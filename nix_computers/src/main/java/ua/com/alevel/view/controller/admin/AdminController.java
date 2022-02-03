package ua.com.alevel.view.controller.admin;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.config.security.SecurityService;
import ua.com.alevel.facade.AdminFacade;
import ua.com.alevel.facade.OrderFacade;
import ua.com.alevel.facade.PersonalFacade;
import ua.com.alevel.facade.ProductFacade;
import ua.com.alevel.persistence.entity.Product;
import ua.com.alevel.view.dto.response.OrderResponseDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.PersonalResponseDto;
import ua.com.alevel.view.dto.response.ProductResponseDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductFacade productFacade;
    private final PersonalFacade personalFacade;
    private final AdminFacade adminFacade;
    private final OrderFacade orderFacade;
    private final SecurityService securityService;

    public AdminController(ProductFacade productFacade, PersonalFacade personalFacade, AdminFacade adminFacade, OrderFacade orderFacade, SecurityService securityService) {
        this.productFacade = productFacade;
        this.personalFacade = personalFacade;
        this.adminFacade = adminFacade;
        this.orderFacade = orderFacade;
        this.securityService = securityService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, WebRequest webrequest) {
        long userCount = personalFacade.findAll(webrequest).getItemsSize();
        long productCount = productFacade.findAll(webrequest).getItemsSize();
        long orderCount = orderFacade.findAll(webrequest).getItemsSize();
        BigDecimal revenue = orderFacade.revenue();
        List<ProductResponseDto> topSales = productFacade.topSales().subList(0, 5);

        model.addAttribute("topSales", topSales);
        model.addAttribute("userCount", userCount);
        model.addAttribute("productCount", productCount);
        model.addAttribute("orderCount", orderCount);
        model.addAttribute("revenue", revenue);
        return "admin_dashboard_main";
    }

    @GetMapping("/users")
    public String userList(WebRequest request, Model model) {
        PageData<PersonalResponseDto> response = personalFacade.findAll(request);
        response.initPaginationState(response.getCurrentPage());
        model.addAttribute("createUrl", "/admin/users");
        model.addAttribute("pageData", response);

        List<PersonalResponseDto> users = personalFacade.findAll(request).getItems();
        model.addAttribute("users", users);
        return "admin_users";
    }
    @PostMapping("/users")
    public ModelAndView usersPaginationRequest(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/admin/users", model);
    }


    @GetMapping("/orders")
    public String orderList(WebRequest request, Model model) {
        PageData<OrderResponseDto> response = orderFacade.findAll(request);
        response.initPaginationState(response.getCurrentPage());
        model.addAttribute("createUrl", "/admin/orders");
        model.addAttribute("pageData", response);

        List<OrderResponseDto> orders = orderFacade.findAll(request).getItems();
        model.addAttribute("orders", orders);
        return "admin_orders";
    }

    @PostMapping("/orders")
    public ModelAndView ordersPagination(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/admin/orders", model);
    }
}
