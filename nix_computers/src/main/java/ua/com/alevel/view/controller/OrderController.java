package ua.com.alevel.view.controller;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import ua.com.alevel.config.security.SecurityService;
import ua.com.alevel.facade.AdminFacade;
import ua.com.alevel.facade.PersonalFacade;
import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.type.Type;
import ua.com.alevel.util.SecurityUtil;
import ua.com.alevel.view.dto.request.OrderRequestDto;
import ua.com.alevel.view.dto.request.PersonalRequestDto;
import ua.com.alevel.view.dto.response.OrderResponseDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.ProductResponseDto;
import ua.com.alevel.facade.OrderFacade;
import ua.com.alevel.facade.ProductFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ua.com.alevel.util.WebRequestUtil.DEFAULT_ORDER_PARAM_VALUE;

@Controller
@RequestMapping("/orders")

public class
OrderController extends AbstractController {

    private final OrderFacade orderFacade;
    private final ProductFacade productFacade;
    private final PersonalFacade personalFacade;
    private final AdminFacade adminFacade;
    private final SecurityService securityService;

    public OrderController(OrderFacade orderFacade, ProductFacade productFacade, PersonalFacade personalFacade, AdminFacade adminFacade, SecurityService securityService) {
        this.orderFacade = orderFacade;
        this.productFacade = productFacade;
        this.personalFacade = personalFacade;
        this.adminFacade = adminFacade;
        this.securityService = securityService;
    }

    @GetMapping("/{userId}") // Страница заказов пользователя
    public String userOrdersPage(@PathVariable Long userId, Model model, WebRequest request) {
        List<Order> orders = orderFacade.findOrdersByUserId(userId);
        model.addAttribute("orders", orders);
        return "order";
    }

    @GetMapping("/remove/{orderId}/{productId}") // Удаление продукта из заказа, и удаление заказа, если 0 продуктов.
    public String removeProductFromOrder(@PathVariable Long productId, @PathVariable Long orderId, Model model) {
        orderFacade.removeProduct(orderId, productId);
        Long userId = securityService.currentUser().getId();
        List<Order> orders = orderFacade.findOrdersByUserId(userId);

        orders.stream().filter(order -> order.getProducts().size() == 0).forEach(order -> {
            personalFacade.removeOrder(userId, order.getId());
            orderFacade.delete(orderId);
        });
        return "redirect:/orders/" + userId;
    }
}
