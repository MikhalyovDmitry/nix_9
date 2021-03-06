package ua.com.alevel.view.controller;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.view.dto.response.OrderResponseDto;
import ua.com.alevel.view.dto.request.ProductRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.ProductResponseDto;
import ua.com.alevel.facade.OrderFacade;
import ua.com.alevel.facade.ProductFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ua.com.alevel.util.WebRequestUtil.DEFAULT_ORDER_PARAM_VALUE;

@Controller
@RequestMapping("/products")
public class ProductController extends AbstractController {

    private final ProductFacade productFacade;
    private final OrderFacade orderFacade;

    public ProductController(ProductFacade productFacade, OrderFacade orderFacade) {
        this.productFacade = productFacade;
        this.orderFacade = orderFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        HeaderName[] columnNames = new HeaderName[] {
                new HeaderName("#", null, null),
                new HeaderName("Name", "name", "name"),
                new HeaderName("Price", "price", "price"),
                new HeaderName("In stock", "inStock", "inStock"),
                new HeaderName("", null, null),
                new HeaderName("", null, null),
                new HeaderName("", null, null)
        };
        PageData<ProductResponseDto> response = productFacade.findAll(webRequest);
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
        model.addAttribute("createUrl", "/products/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Products");
        return "pages/product/product_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/products", model);
    }

    @GetMapping("/new")
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
        List<OrderResponseDto> orders = productFacade.getOrders(id);
        model.addAttribute("product", productFacade.findById(id));
        model.addAttribute("orders", orders);
        return "pages/product/product_details";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        List<OrderResponseDto> orders = productFacade.getOrders(id);
        for (OrderResponseDto order: orders) {
            orderFacade.removeProduct(order.getId(), id);
        }
        productFacade.delete(id);
        return "redirect:/products";
    }

    @GetMapping("/add/{id}")
    public String redirectToAddProductPage(@PathVariable Long id, Model model, WebRequest request) {
        List<ProductResponseDto> products = productFacade.findAll(request).getItems();
        model.addAttribute("products", products);
        model.addAttribute("order", orderFacade.findById(id));
        return "pages/product/product_add";
    }

    @GetMapping("/order/{productId}/{orderId}")
    public String addProduct(@PathVariable Long productId, @PathVariable Long orderId, Model model) {
        orderFacade.addProduct(orderId, productId);
        List<ProductResponseDto> products = orderFacade.getProducts(orderId);
        model.addAttribute("order", orderFacade.findById(orderId));
        model.addAttribute("products", products);
        return "pages/order/order_details";
    }

    @GetMapping("/delete/order/{productId}/{orderId}")
    public String deleteProductFromOrder(@PathVariable Long productId, @PathVariable Long orderId, Model model) {
        orderFacade.removeProduct(orderId, productId);
        List<ProductResponseDto> products = orderFacade.getProducts(orderId);
        model.addAttribute("order", orderFacade.findById(orderId));
        model.addAttribute("products", products);
        return "pages/order/order_details";
    }
}
