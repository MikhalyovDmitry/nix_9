package ua.com.alevel.facade;

import ua.com.alevel.dto.order.OrderResponseDto;
import ua.com.alevel.dto.orderproduct.OrderProductRequestDto;
import ua.com.alevel.dto.orderproduct.OrderProductResponseDto;
import ua.com.alevel.dto.product.ProductResponseDto;

import java.util.List;

public interface OrderProductFacade extends BaseFacade<OrderProductRequestDto, OrderProductResponseDto> {

    List<ProductResponseDto> findByOrderId(Long orderId);

    List<OrderResponseDto> findByProductId(Long orderId);

    OrderProductResponseDto findByOrderIdAndProductId(Long productId, Long orderId);
}
