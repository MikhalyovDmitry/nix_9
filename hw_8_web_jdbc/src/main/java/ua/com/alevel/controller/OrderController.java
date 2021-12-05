package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.order.OrderRequestDto;
import ua.com.alevel.dto.order.OrderResponseDto;
import ua.com.alevel.dto.orderproduct.OrderProductRequestDto;
import ua.com.alevel.dto.product.ProductResponseDto;
import ua.com.alevel.facade.OrderFacade;
import ua.com.alevel.facade.OrderProductFacade;
import ua.com.alevel.facade.ProductFacade;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderFacade orderFacade;
    private final ProductFacade productFacade;
    private final OrderProductFacade orderProductFacade;

    public OrderController(OrderFacade orderFacade, ProductFacade productFacade, OrderProductFacade orderProductFacade) {
        this.orderFacade = orderFacade;
        this.productFacade = productFacade;
        this.orderProductFacade = orderProductFacade;
    }

    @GetMapping
    public String findAll(Model model) {
        List<OrderResponseDto> orders = orderFacade.findAll();
        model.addAttribute("orders", orders);
        return "pages/order/order_all";
    }

    @GetMapping("/new")
    public String redirectToNewOrderPage(Model model) {
        model.addAttribute("order", new OrderRequestDto());
        return "pages/order/order_new";
    }

    @PostMapping("/new")
    public String createNewOrder(@ModelAttribute("order") OrderRequestDto orderRequestDto) {
        orderFacade.create(orderRequestDto);
        return "redirect:/orders";
    }

    //Для этого метода написать html order_update
    @GetMapping("/update/{id}")
    public String redirectToUpdateOrderPage(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderFacade.findById(id));
        return "pages/order/order_update";
    }

    @PostMapping("/update/{id}")
    public String updateOrder(@ModelAttribute("order") OrderRequestDto orderRequestDto, @PathVariable Long id) {
        orderFacade.update(orderRequestDto, id);
        return "redirect:/orders";
    }

    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        List<ProductResponseDto> products = orderProductFacade.findByOrderId(id);
        model.addAttribute("order", orderFacade.findById(id));
        model.addAttribute("products", products);
        return "pages/order/order_details";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        List<ProductResponseDto> products = orderProductFacade.findByOrderId(id);
        products.stream()
                .map(product -> orderProductFacade.findByOrderIdAndProductId(product.getId(), id).getId())
                .forEach(orderProductFacade::delete);
        orderFacade.delete(id);
        return "redirect:/orders";
    }

    @GetMapping("/add/{id}")
    public String redirectToAddOrderPage(@PathVariable Long id, Model model) {
        List<OrderResponseDto> orders = orderFacade.findAll();
        model.addAttribute("orders", orders);
        model.addAttribute("product", productFacade.findById(id));
        return "pages/order/order_add";
    }

    @GetMapping("/order/{productId}/{orderId}")
    public String addProduct(@PathVariable Long productId, @PathVariable Long orderId, Model model) {
        OrderProductRequestDto dto = new OrderProductRequestDto();
        dto.setProductId(productId);
        dto.setOrderId(orderId);
        orderProductFacade.create(dto);
        List<OrderResponseDto> orders = orderProductFacade.findByProductId(productId);
        model.addAttribute("product", productFacade.findById(productId));
        model.addAttribute("orders", orders);
        return "pages/product/product_details";
    }

    @GetMapping("/delete/order/{productId}/{orderId}")
    public String deleteProductFromOrder(@PathVariable Long productId, @PathVariable Long orderId, Model model) {
        Long id = orderProductFacade.findByOrderIdAndProductId(productId, orderId).getId();
        orderProductFacade.delete(id);
        List<OrderResponseDto> orders = orderProductFacade.findByProductId(productId);
        model.addAttribute("product", productFacade.findById(productId));
        model.addAttribute("orders", orders);
        return "pages/product/product_details";
    }

}
