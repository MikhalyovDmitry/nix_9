package ua.com.alevel.web.controller.open;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.validation.ValidId;

import javax.validation.constraints.Min;

@Validated
@Controller
@RequestMapping("/books/details")
public class PDPController {

    @GetMapping("/{id}")
    public String pdp(@PathVariable @Min(value = 1, message = "idiot") Long id) {
        System.out.println("id = " + id);
        return "pages/open/pdp";
    }

    @GetMapping("/test/{id}")
    public String pdpTest(@PathVariable @ValidId(message = "double idiot") Long id) {
        System.out.println("id = " + id);
        return "pages/open/pdp";
    }
}
