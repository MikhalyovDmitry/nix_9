package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.order.OrderResponseDto;
import ua.com.alevel.dto.orderproduct.OrderProductRequestDto;
import ua.com.alevel.dto.product.ProductRequestDto;
import ua.com.alevel.dto.product.ProductResponseDto;
import ua.com.alevel.facade.OrderFacade;
import ua.com.alevel.facade.OrderProductFacade;
import ua.com.alevel.facade.ProductFacade;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductFacade productFacade;
    private final OrderFacade orderFacade;
    private final OrderProductFacade orderProductFacade;

    public ProductController(ProductFacade productFacade, OrderFacade orderFacade, OrderProductFacade orderProductFacade) {
        this.productFacade = productFacade;
        this.orderFacade = orderFacade;
        this.orderProductFacade = orderProductFacade;
    }

    @GetMapping
    public String findAll(Model model) {
        List<ProductResponseDto> products = productFacade.findAll();
        model.addAttribute("products", products);
        return "pages/product/product_all";
    }

    @GetMapping("/new")
    //@GetMapping("/new/{departmentId}")
    public String redirectToNewProductPage(Model model) {
        model.addAttribute("product", new ProductRequestDto());
        return "pages/product/product_new";
    }

    @PostMapping("/new")
    public String createNewProduct(@ModelAttribute("product") ProductRequestDto dto) {
        productFacade.create(dto);
        return "redirect:/products";
    }

    @GetMapping("/update/{id}")
    public String redirectToUpdateProductPage(@PathVariable Long id, Model model) {
        model.addAttribute("product", productFacade.findById(id));
        return "pages/product/product_update";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@ModelAttribute("product") ProductRequestDto productRequestDto, @PathVariable Long id) {
        productFacade.update(productRequestDto, id);
        return "redirect:/products";
    }

    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        List<OrderResponseDto> orders = orderProductFacade.findByProductId(id);
        model.addAttribute("product", productFacade.findById(id));
        model.addAttribute("orders", orders);
        return "pages/product/product_details";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        List<OrderResponseDto> orders = orderProductFacade.findByProductId(id);
        orders.stream()
                .map(order -> orderProductFacade.findByOrderIdAndProductId(id, order.getId()).getId())
                .forEach(orderProductFacade::delete);
        productFacade.delete(id);
        return "redirect:/products";
    }

    @GetMapping("/add/{id}")
    public String redirectToAddProductPage(@PathVariable Long id, Model model) {
        List<ProductResponseDto> products = productFacade.findAll();
        model.addAttribute("products", products);
        model.addAttribute("order", orderFacade.findById(id));
        return "pages/product/product_add";
    }

    @GetMapping("/order/{productId}/{orderId}")
    public String addProduct(@PathVariable Long productId, @PathVariable Long orderId, Model model) {
        OrderProductRequestDto dto = new OrderProductRequestDto();
        dto.setProductId(productId);
        dto.setOrderId(orderId);
        orderProductFacade.create(dto);
        model.addAttribute("order", orderFacade.findById(orderId));
        List<ProductResponseDto> products = orderProductFacade.findByOrderId(orderId);
        model.addAttribute("products", products);
        return "pages/order/order_details";
    }

    @GetMapping("/delete/order/{productId}/{orderId}")
    public String deleteProductFromOrder(@PathVariable Long productId, @PathVariable Long orderId, Model model) {
        Long id = orderProductFacade.findByOrderIdAndProductId(productId, orderId).getId();
        orderProductFacade.delete(id);
        model.addAttribute("order", orderFacade.findById(orderId));
        List<ProductResponseDto> products = orderProductFacade.findByOrderId(orderId);
        model.addAttribute("products", products);
        return "pages/order/order_details";
    }


}
