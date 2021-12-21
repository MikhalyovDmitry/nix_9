package ua.com.alevel.view.controller;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import ua.com.alevel.type.Type;
import ua.com.alevel.view.dto.request.OrderRequestDto;
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

    public OrderController(OrderFacade orderFacade, ProductFacade productFacade) {
        this.orderFacade = orderFacade;
        this.productFacade = productFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        HeaderName[] columnNames = new HeaderName[] {
                new HeaderName("#", null, null),
                new HeaderName("Customer", "customer", "customer"),
                new HeaderName("Delivered", "delivered", "delivered"),
                new HeaderName("Created", "created", "created"),
                new HeaderName("", null, null),
                new HeaderName("", null, null),
                new HeaderName("", null, null)
        };
        PageData<OrderResponseDto> response = orderFacade.findAll(webRequest);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = new ArrayList<>();
        for (HeaderName headerName : columnNames) {
            HeaderData data = new HeaderData();
            data.setHeaderName(headerName.getColumnName());
            if (StringUtils.isBlank(headerName.getTableName())) {
                data.setSortable(false);
            } else {
                data.setSortable(true);
                data.setSort(headerName.getDbName());
                if (response.getSort().equals(headerName.getTableName())) {
                    data.setActive(true);
                    data.setOrder(response.getOrder());
                } else {
                    data.setActive(false);
                    data.setOrder(DEFAULT_ORDER_PARAM_VALUE);
                }
            }
            headerDataList.add(data);
        }

        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/orders/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Orders");
        return "pages/order/order_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/orders", model);
    }

    @GetMapping("/new")
    public String redirectToNewOrderPage(Model model) {
        model.addAttribute("order", new OrderRequestDto());
        model.addAttribute("types", Type.values());
        return "pages/order/order_new";
    }

    @PostMapping("/new")
    public String createNewOrder(@ModelAttribute("order") OrderRequestDto orderRequestDto) {
        orderFacade.create(orderRequestDto);
        return "redirect:/orders";
    }

    @GetMapping("/update/{id}")
    public String redirectToUpdateOrderPage(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderFacade.findById(id));
        model.addAttribute("types", Type.values());
        return "pages/order/order_update";
    }

    @PostMapping("/update/{id}")
    public String updateOrder(@ModelAttribute("order") OrderRequestDto orderRequestDto, @PathVariable Long id) {
        orderFacade.update(orderRequestDto, id);
        return "redirect:/orders";
    }

    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        List<ProductResponseDto> products = orderFacade.getProducts(id);
        model.addAttribute("order", orderFacade.findById(id));
        model.addAttribute("products", products);
        return "pages/order/order_details";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        List<ProductResponseDto> products = orderFacade.getProducts(id);
        for (ProductResponseDto product: products) {
            orderFacade.removeProduct(id, product.getId());
        }
        orderFacade.delete(id);
        return "redirect:/orders";
    }

    @GetMapping("/add/{id}")
    public String redirectToAddOrderPage(@PathVariable Long id, Model model, WebRequest request) {
        List<OrderResponseDto> orders = orderFacade.findAll(request).getItems();
        model.addAttribute("orders", orders);
        model.addAttribute("product", productFacade.findById(id));
        return "pages/order/order_add";
    }

    @GetMapping("/order/{productId}/{orderId}")
    public String addProduct(@PathVariable Long productId, @PathVariable Long orderId, Model model) {
        orderFacade.addProduct(orderId, productId);
        List<OrderResponseDto> orders = productFacade.getOrders(productId);
        model.addAttribute("product", productFacade.findById(productId));
        model.addAttribute("orders", orders);
        return "pages/product/product_details";
    }

    @GetMapping("/delete/order/{productId}/{orderId}")
    public String deleteProductFromOrder(@PathVariable Long productId, @PathVariable Long orderId, Model model) {
        orderFacade.removeProduct(orderId, productId);
        List<OrderResponseDto> orders = productFacade.getOrders(productId);
        model.addAttribute("product", productFacade.findById(productId));
        model.addAttribute("orders", orders);
        return "pages/product/product_details";
    }

}
