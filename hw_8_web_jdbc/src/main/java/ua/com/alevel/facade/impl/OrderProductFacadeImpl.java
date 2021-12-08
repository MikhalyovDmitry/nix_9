package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.response.OrderResponseDto;
import ua.com.alevel.view.dto.request.OrderProductRequestDto;
import ua.com.alevel.view.dto.response.OrderProductResponseDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.ProductResponseDto;
import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.persistence.entity.OrderProduct;
import ua.com.alevel.persistence.entity.Product;
import ua.com.alevel.facade.OrderProductFacade;
import ua.com.alevel.service.OrderProductService;
import ua.com.alevel.service.OrderService;
import ua.com.alevel.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderProductFacadeImpl implements OrderProductFacade {

    private final OrderProductService orderProductService;
    private final ProductService productService;
    private final OrderService orderService;

    public OrderProductFacadeImpl(OrderProductService orderProductService, ProductService productService, OrderService orderService) {
        this.orderProductService = orderProductService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @Override
    public void create(OrderProductRequestDto orderProductRequestDto) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrderId(orderProductRequestDto.getOrderId());
        orderProduct.setProductId(orderProductRequestDto.getProductId());
        orderProductService.create(orderProduct);
    }

    @Override
    public void update(OrderProductRequestDto orderProductRequestDto, Long id) {
        OrderProduct orderProduct = orderProductService.findById(id);
        orderProduct.setOrderId(orderProductRequestDto.getOrderId());
        orderProduct.setProductId(orderProductRequestDto.getProductId());
        orderProductService.update(orderProduct);
    }

    @Override
    public void delete(Long id) {
        orderProductService.delete(id);
    }

    @Override
    public OrderProductResponseDto findById(Long id) {
        OrderProduct orderProduct = orderProductService.findById(id);
        return new OrderProductResponseDto(orderProduct);
    }

    @Override
    public List<ProductResponseDto> findByOrderId(Long orderId) {
        List<OrderProduct> orderProducts;
        List<ProductResponseDto> products = new ArrayList<>();
        orderProducts = orderProductService.findByOrderId(orderId);
        Product product;
        ProductResponseDto productResponseDto;
        for (OrderProduct orderProduct : orderProducts) {
            if (orderProduct.getOrderId().equals(orderId)) {
                product = productService.findById(orderProduct.getProductId());
                productResponseDto = new ProductResponseDto(product);
                products.add(productResponseDto);
            }
        }
        return products;
    }

    @Override
    public List<OrderResponseDto> findByProductId(Long productId) {
        List<OrderProduct> orderProducts;
        List<OrderResponseDto> orders = new ArrayList<>();
        orderProducts = orderProductService.findByProductId(productId);
        Order order;
        OrderResponseDto orderResponseDto;
        for (OrderProduct orderProduct : orderProducts) {
            if (orderProduct.getProductId().equals(productId)) {
                order = orderService.findById(orderProduct.getOrderId());
                orderResponseDto = new OrderResponseDto(order);
                orders.add(orderResponseDto);
            }
        }
        return orders;
    }

    @Override
    public OrderProductResponseDto findByOrderIdAndProductId(Long productId, Long orderId) {
        OrderProduct orderProduct = orderProductService.findByOrderIdAndProductId(productId, orderId);
        return new OrderProductResponseDto(orderProduct);
    }

//    @Override
//    public List<OrderProductResponseDto> findAll() {
//        return orderProductService.findAll()
//                .stream()
//                .map(OrderProductResponseDto::new)
//                .collect(Collectors.toList());
//    }

    @Override
    public PageData<OrderProductResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setPageSize(pageAndSizeData.getSize());
        dataTableRequest.setCurrentPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<OrderProduct> all = orderProductService.findAll(dataTableRequest);

        List<OrderProductResponseDto> list = all.getItems().
                stream().
                map(OrderProductResponseDto::new).
                collect(Collectors.toList());

        PageData<OrderProductResponseDto> pageData = new PageData<>();
        pageData.setItems(list);
        pageData.setCurrentPage(pageAndSizeData.getPage());
        pageData.setPageSize(pageAndSizeData.getSize());
        pageData.setOrder(sortData.getOrder());
        pageData.setSort(sortData.getSort());
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());

        System.out.println("FROM " + pageData.getCurrentShowFromEntries() + ", TO " + pageData.getCurrentShowToEntries());

        System.out.println("pageData = " + pageData);
        return pageData;
    }
}
