package ua.com.alevel.facade;

import ua.com.alevel.view.dto.response.OrderResponseDto;
import ua.com.alevel.view.dto.request.OrderProductRequestDto;
import ua.com.alevel.view.dto.response.OrderProductResponseDto;
import ua.com.alevel.view.dto.response.ProductResponseDto;

import java.util.List;

public interface OrderProductFacade extends BaseFacade<OrderProductRequestDto, OrderProductResponseDto> {

    List<ProductResponseDto> findByOrderId(Long orderId);

    List<OrderResponseDto> findByProductId(Long orderId);

    OrderProductResponseDto findByOrderIdAndProductId(Long productId, Long orderId);
}
