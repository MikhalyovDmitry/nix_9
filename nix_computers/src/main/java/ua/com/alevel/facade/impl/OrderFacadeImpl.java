package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Product;
import ua.com.alevel.service.ProductService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.view.dto.request.OrderRequestDto;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.response.OrderResponseDto;
import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.facade.OrderFacade;
import ua.com.alevel.service.OrderService;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.ProductResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderFacadeImpl implements OrderFacade {

    private final OrderService orderService;
    private final ProductService productService;

    public OrderFacadeImpl(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @Override
    public void addProduct(Long orderId, Long productId) {
        Order order = orderService.findById(orderId).get();
        Product product = productService.findById(productId).get();
        order.addProduct(product);
        orderService.update(order);
    }

    @Override
    public void removeProduct(Long orderId, Long productId) {
        Order order = orderService.findById(orderId).get();
        Product product = productService.findById(productId).get();
        order.removeProduct(product);
        orderService.update(order);
    }

    @Override
    public List<ProductResponseDto> getProducts(Long id) {
        List<Product> products = orderService.findById(id).get().getProducts();
        List<ProductResponseDto> list = new ArrayList<>();
        for (Product product : products) {
            ProductResponseDto productResponseDto = new ProductResponseDto(product);
            list.add(productResponseDto);
        }
        return list;
    }

    @Override
    public void create(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setCustomer(orderRequestDto.getCustomer());
        order.setDelivered(orderRequestDto.isDelivered());
        orderService.create(order);
    }

    @Override
    public void update(OrderRequestDto orderRequestDto, Long id) {
        Order order = orderService.findById(id).get();
        order.setCustomer(orderRequestDto.getCustomer());
        order.setDelivered(orderRequestDto.isDelivered());
        orderService.update(order);
    }

    @Override
    public void delete(Long id) {
        orderService.delete(id);
    }

    @Override
    public OrderResponseDto findById(Long id) {
        Order order = orderService.findById(id).get();
        return new OrderResponseDto(order);
    }

    @Override
    public PageData<OrderResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setSize(pageAndSizeData.getSize());
        dataTableRequest.setPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<Order> all = orderService.findAll(dataTableRequest);

        List<OrderResponseDto> list = all.getItems().
                stream().
                map(OrderResponseDto::new).
                collect(Collectors.toList());

        PageData<OrderResponseDto> pageData = new PageData<>();
        pageData.setItems(list);
        pageData.setCurrentPage(pageAndSizeData.getPage());
        pageData.setPageSize(pageAndSizeData.getSize());
        pageData.setOrder(sortData.getOrder());
        pageData.setSort(sortData.getSort());
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());

        return pageData;
    }
}
