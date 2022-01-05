package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ua.com.alevel.config.security.SecurityService;

@Controller
public class MainController {

    private final SecurityService securityService;

    public MainController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping
    public String main() {
        return "redirect:/products/magic";
    }
}
