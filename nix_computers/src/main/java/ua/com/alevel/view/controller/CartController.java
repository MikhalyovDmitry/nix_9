package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.alevel.config.security.SecurityService;
import ua.com.alevel.facade.*;
import ua.com.alevel.persistence.entity.Product;
import ua.com.alevel.service.ProductService;
import ua.com.alevel.util.SecurityUtil;
import ua.com.alevel.view.dto.request.CartRequestDto;
import ua.com.alevel.view.dto.request.OrderRequestDto;
import ua.com.alevel.view.dto.response.CartResponseDto;
import ua.com.alevel.view.dto.response.OrderResponseDto;
import ua.com.alevel.view.dto.response.ProductResponseDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartFacade cartFacade;
    private final ProductFacade productFacade;
    private final PersonalFacade personalFacade;
    private final SecurityService securityService;
    private final OrderFacade orderFacade;
    private final ProductService productService;
    private final AdminFacade adminFacade;


    public CartController(CartFacade cartFacade, ProductFacade productFacade, PersonalFacade personalFacade, SecurityService securityService, OrderFacade orderFacade, ProductService productService, AdminFacade adminFacade) {
        this.cartFacade = cartFacade;
        this.productFacade = productFacade;
        this.personalFacade = personalFacade;
        this.securityService = securityService;
        this.orderFacade = orderFacade;
        this.productService = productService;
        this.adminFacade = adminFacade;
    }

    @GetMapping("/{userId}/{productId}") // Добавление продукта в корзину пользователя
    public String productToCart(@PathVariable Long productId, Model model, RedirectAttributes redirectAttributes) {
        CartRequestDto cartRequestDto = new CartRequestDto();
        cartRequestDto.setProductId(productId);
        Long userId = securityService.currentUser().getId();
        cartRequestDto.setUserId(userId);
        cartFacade.create(cartRequestDto);

        boolean buttonsVisibility = adminFacade.findByName(SecurityUtil.getUsername()) == null;

        redirectAttributes.addFlashAttribute("buttonsVisibility", buttonsVisibility);
        redirectAttributes.addFlashAttribute("message", "Product added to cart");
        redirectAttributes.addFlashAttribute("visibility", true);
        return "redirect:/products/details/" + productId;
    }

    @GetMapping("/{userId}")
    public String cart(@PathVariable Long userId, WebRequest webRequest, Model model, RedirectAttributes redirectAttributes) {
        List<CartResponseDto> cart = cartFacade.cartByUserId(userId, webRequest);
        List<ProductResponseDto> products = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        int count = 0;
        for (CartResponseDto dto: cart) {
            products.add(productFacade.findById(dto.getProductId()));
            totalPrice = totalPrice.add(productFacade.findById(dto.getProductId()).getPrice());
            count++;
        }

        boolean buyNowButtonVisibility = true;
        if (cart.size() == 0) {
            buyNowButtonVisibility = false;
        }

        boolean nameInputVisibility = true;
        if (securityService.currentPersonal().getFullName() != null) {
            nameInputVisibility = false;
        }

        model.addAttribute("nameInputVisibility", nameInputVisibility);
        model.addAttribute("buyNowButtonVisibility", buyNowButtonVisibility);
        model.addAttribute("userId", userId);
        model.addAttribute("products", products);
        model.addAttribute("totalPrice", totalPrice.setScale(2));
        model.addAttribute("count", count);
        model.addAttribute("order", new OrderRequestDto());
        return "/cart";
    }

    @PostMapping("/{userId}")
    public String cart(@ModelAttribute("order") OrderRequestDto dto, @PathVariable Long userId, WebRequest webRequest, RedirectAttributes redirectAttributes) {
        String email = personalFacade.findById(userId).getEmail();
        List<CartResponseDto> cart = cartFacade.cartByUserId(userId, webRequest);
        List<ProductResponseDto> productsDto = cart.
                stream().map(cartResponseDto ->
                        productFacade.findById(cartResponseDto.getProductId())).collect(Collectors.toList());

        List<Product> products = productsDto.
                stream().
                map(product -> productService.findById(product.getId()).get()).collect(Collectors.toList());

        dto.setProducts(products);
        dto.setEmail(email);
        if (securityService.currentPersonal().getFullName() != null) {
            dto.setName(securityService.currentPersonal().getFullName());
        }
        orderFacade.create(dto);
        Long orderId = orderFacade.lastCreated();
        personalFacade.addOrderToUser(userId, orderId);
        cartFacade.deleteCartByUsersId(userId);

        OrderResponseDto order = orderFacade.findById(orderId);
        redirectAttributes.addFlashAttribute("order", order);

        return "redirect:/cart/buy/" + userId;
    }

    @GetMapping("/buy/{userId}")
    public String buy(RedirectAttributes redirectAttributes, Model model, @PathVariable Long userId) {
        boolean ok = true;
        redirectAttributes.addFlashAttribute("ok", ok);

        OrderResponseDto order = (OrderResponseDto) model.asMap().get("order");
        redirectAttributes.addFlashAttribute("name", order.getName());
        return "redirect:/cart/" + userId;
    }


    @GetMapping("/remove/{userId}/{productId}")
    public String removeFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        List<Long> cartIds = cartFacade.findCartIdByUserIdAndProductId(userId, productId);
        if (cartIds.get(0) != null) {
            cartFacade.delete(cartIds.get(0));
        }
        return "redirect:/cart/" + userId;
    }
}
