package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.alevel.config.security.SecurityService;
import ua.com.alevel.facade.CartFacade;
import ua.com.alevel.facade.PersonalFacade;
import ua.com.alevel.facade.ProductFacade;
import ua.com.alevel.util.SecurityUtil;
import ua.com.alevel.view.dto.request.CartRequestDto;
import ua.com.alevel.view.dto.response.CartResponseDto;
import ua.com.alevel.view.dto.response.ProductResponseDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartFacade cartFacade;
    private final ProductFacade productFacade;
    private final PersonalFacade personalFacade;
    private final SecurityService securityService;

    public CartController(CartFacade cartFacade, ProductFacade productFacade, PersonalFacade personalFacade, SecurityService securityService) {
        this.cartFacade = cartFacade;
        this.productFacade = productFacade;

        this.personalFacade = personalFacade;
        this.securityService = securityService;
    }

    @GetMapping("/{userId}/{productId}")
    public String productToCart(@PathVariable Long productId, Model model, RedirectAttributes redirectAttributes) {
        CartRequestDto cartRequestDto = new CartRequestDto();
        cartRequestDto.setProductId(productId);

        boolean isAuthenticated = securityService.isAuthenticated();
        Long userId = null;
        if (isAuthenticated) {
            userId = personalFacade.findByName(SecurityUtil.getUsername());
        }
        cartRequestDto.setUserId(userId);
        cartFacade.create(cartRequestDto);

        redirectAttributes.addFlashAttribute("message", "Product added to cart");
        redirectAttributes.addFlashAttribute("visibility", true);
        return "redirect:/products/details/" + productId;
    }

    @GetMapping("/{userId}")
    public String cart(@PathVariable Long userId, WebRequest webRequest, Model model) {
        List<CartResponseDto> cart = cartFacade.cartByUserId(userId, webRequest);
        List<ProductResponseDto> products = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        int count = 0;
        for (CartResponseDto dto: cart) {
            products.add(productFacade.findById(dto.getProductId()));
            totalPrice = totalPrice.add(productFacade.findById(dto.getProductId()).getPrice());
            count++;

        }
        model.addAttribute("products", products);
        model.addAttribute("totalPrice", totalPrice.setScale(2));
        model.addAttribute("count", count);

        return "/cart";
    }
}
