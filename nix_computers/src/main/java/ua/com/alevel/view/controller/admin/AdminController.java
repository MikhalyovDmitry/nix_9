package ua.com.alevel.view.controller.admin;

import org.hibernate.sql.ordering.antlr.OrderByAliasResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.config.security.SecurityService;
import ua.com.alevel.facade.AdminFacade;
import ua.com.alevel.facade.OrderFacade;
import ua.com.alevel.facade.PersonalFacade;
import ua.com.alevel.facade.ProductFacade;
import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.util.SecurityUtil;
import ua.com.alevel.view.dto.request.OrderRequestDto;
import ua.com.alevel.view.dto.response.OrderResponseDto;
import ua.com.alevel.view.dto.response.PersonalResponseDto;

import java.math.BigDecimal;
import java.util.List;

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

        model.addAttribute("userCount", userCount);
        model.addAttribute("productCount", productCount);
        model.addAttribute("orderCount", orderCount);
        model.addAttribute("revenue", revenue);
        return "admin_dashboard_main";
    }

    @GetMapping("/users")
    public String userList(WebRequest request, Model model) {
        List<PersonalResponseDto> users = personalFacade.findAll(request).getItems();
        System.out.println();
        Long userId = null;
        boolean isAuthenticated = securityService.isAuthenticated();
        if (isAuthenticated) {
            if (adminFacade.findByName(SecurityUtil.getUsername()) != null) {
                userId = adminFacade.findByName(SecurityUtil.getUsername());
            }
            if (personalFacade.findByName(SecurityUtil.getUsername()) != null) {
                userId = personalFacade.findByName(SecurityUtil.getUsername());
            }
        }

        model.addAttribute("users", users);
        return "admin_users";
    }

    @GetMapping("/orders")
    public String orderList(WebRequest request, Model model) {
        List<OrderResponseDto> orders = orderFacade.findAll(request).getItems();

        model.addAttribute("orders", orders);
        return "admin_orders";
    }
}
