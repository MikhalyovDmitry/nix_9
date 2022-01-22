package ua.com.alevel.facade;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.view.dto.request.OrderRequestDto;
import ua.com.alevel.view.dto.response.OrderResponseDto;
import ua.com.alevel.view.dto.response.ProductResponseDto;

import java.math.BigDecimal;
import java.util.List;

public interface OrderFacade extends BaseFacade<OrderRequestDto, OrderResponseDto> {

    List<ProductResponseDto> getProducts(Long id);

    List<Order> findOrdersByUserId(Long userId);

    void addProduct(Long orderId, Long productId);

    void removeProduct(Long orderId, Long productId);

    Long lastCreated();

    BigDecimal revenue();
}
