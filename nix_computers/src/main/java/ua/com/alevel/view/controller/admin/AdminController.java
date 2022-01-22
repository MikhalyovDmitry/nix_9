package ua.com.alevel.view.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.OrderFacade;
import ua.com.alevel.facade.PersonalFacade;
import ua.com.alevel.facade.ProductFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;

import java.math.BigDecimal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductFacade productFacade;
    private final PersonalFacade personalFacade;
    private final OrderFacade orderFacade;

    public AdminController(ProductFacade productFacade, PersonalFacade personalFacade, OrderFacade orderFacade) {
        this.productFacade = productFacade;
        this.personalFacade = personalFacade;
        this.orderFacade = orderFacade;
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
}
