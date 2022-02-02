package ua.com.alevel.view.controller;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.alevel.config.security.SecurityService;
import ua.com.alevel.facade.*;
import ua.com.alevel.persistence.entity.Product;
import ua.com.alevel.persistence.type.RoleType;
import ua.com.alevel.util.SecurityUtil;
import ua.com.alevel.view.dto.request.OrderRequestDto;
import ua.com.alevel.view.dto.response.OrderResponseDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.ProductResponseDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/products")
public class ProductController extends AbstractController {

    private final ProductFacade productFacade;
    private final OrderFacade orderFacade;
    private final PersonalFacade personalFacade;
    private final AdminFacade adminFacade;
    private final CartFacade cartFacade;
    private final SecurityService securityService;

    public ProductController(ProductFacade productFacade, OrderFacade orderFacade, PersonalFacade personalFacade, AdminFacade adminFacade, CartFacade cartFacade, SecurityService securityService) {
        this.productFacade = productFacade;
        this.orderFacade = orderFacade;
        this.personalFacade = personalFacade;
        this.adminFacade = adminFacade;
        this.cartFacade = cartFacade;
        this.securityService = securityService;
    }

    @GetMapping("/magic") // Главная страница
    public String productPage(Model model, WebRequest webRequest) {
        PageData<ProductResponseDto> response = productFacade.findAll(webRequest);
        response.initPaginationState(response.getCurrentPage());

        model.addAttribute("createUrl", "/products/all");
        model.addAttribute("pageData", response);

        boolean isAuthenticated = securityService.isAuthenticated();
        if (isAuthenticated) {
            Long userId = securityService.currentUser().getId();
            model.addAttribute("userId", userId);
            if (securityService.currentUser().getRoleType().equals(RoleType.ROLE_ADMIN)) {
                return "admin_plp";
            }
            if (securityService.currentUser().getRoleType().equals(RoleType.ROLE_PERSONAL)) {
                model.addAttribute("cardCounter", cartFacade.cartCountByUserId(userId, webRequest));
                return "auth_plp";
            }
        }
        return "plp";
    }

    @PostMapping("/all") // Пагинация пост метод
    public ModelAndView paginationRequest(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/products/magic", model);
    }

    @GetMapping("/details/{id}") // Страница продукта
    public String findById(@PathVariable Long id, Model model) {
        List<OrderResponseDto> orders = productFacade.getOrders(id);
        model.addAttribute("product", productFacade.findById(id));
        model.addAttribute("orders", orders);
        model.addAttribute("order", new OrderRequestDto());

        Boolean visibility = (Boolean) model.asMap().get("visibility");
        if (visibility != null && visibility) {
            return "pages/product/product";
        }

        boolean isAuthenticated = securityService.isAuthenticated();
        boolean buttonsVisibility = true;
        boolean nameInputVisibility = true;
        boolean phoneInputVisibility = true;
        Long userId = null;
        if (isAuthenticated) {
            userId = securityService.currentUser().getId();
            if (securityService.currentUser().getRoleType().equals(RoleType.ROLE_ADMIN)) {
                buttonsVisibility = false;
            } else {
                if (securityService.currentPersonal().getFullName() != null) {
                    nameInputVisibility = false;
                }
                if (securityService.currentPersonal().getPhone() != null) {
                    phoneInputVisibility = false;
                }
            }
        }
        model.addAttribute("userId", userId);
        model.addAttribute("phoneInputVisibility", phoneInputVisibility);
        model.addAttribute("nameInputVisibility", nameInputVisibility);
        model.addAttribute("visibility", false);
        model.addAttribute("buttonsVisibility", buttonsVisibility);
        model.addAttribute("id", id);
        return "pages/product/product";
    }

    @PostMapping("/details/{id}") // Создание заказа, кнопка купить
    public String orderSubmit(@ModelAttribute("order") OrderRequestDto dto, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        List<Product> products = new ArrayList<>();
        products.add(productFacade.findProductById(id));
        dto.setProducts(products);
        boolean isAuthenticated = securityService.isAuthenticated();
        Long userId = null;
        if (isAuthenticated) {
            userId = securityService.currentUser().getId();
            if (securityService.currentPersonal().getFullName() != null) {
                dto.setName(securityService.currentPersonal().getFullName());
            }
            if (securityService.currentPersonal().getPhone() != null) {
                dto.setPhone(securityService.currentPersonal().getPhone());
            }
        }
        if (userId != null) {
            dto.setEmail(SecurityUtil.getUsername());
        } else {
            dto.setEmail("Unregistered user");
        }
        orderFacade.create(dto);

        Long orderId = orderFacade.lastCreated();
        if (userId != null) {
            personalFacade.addOrderToUser(userId, orderId);
        }

        OrderResponseDto order = orderFacade.findById(orderId);
        redirectAttributes.addFlashAttribute("order", order);
        return "redirect:/products/details/order/" + id;
    }

    @GetMapping("/details/order/{id}") // Метод для активации модального окна с выводом имени заказа
    public String orderFromUnregisteredUser(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {
        boolean ok = true;
        redirectAttributes.addFlashAttribute("ok", ok);

        OrderResponseDto order = (OrderResponseDto) model.asMap().get("order");
        redirectAttributes.addFlashAttribute("name", order.getName());
        return "redirect:/products/details/" + id;
    }
}
