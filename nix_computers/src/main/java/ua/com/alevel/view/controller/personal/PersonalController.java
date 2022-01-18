package ua.com.alevel.view.controller.personal;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.validated.ValidId;

@Validated
@Controller
@RequestMapping("/personal")
public class PersonalController {

    @GetMapping("/{id}")
    public String dashboard(@PathVariable @ValidId(message = "id must be more than zero") Long id) {
        System.out.println("id = " + id);
        return "pages/personal/dashboard";
    }

//    @GetMapping("/cart")
//    public String productCart() {
//
//        return "pages/personal/product_cart";
//    }
}
