package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.OrderRequestDto;
import ua.com.alevel.view.dto.response.OrderResponseDto;
import ua.com.alevel.view.dto.response.ProductResponseDto;

import java.util.List;

public interface OrderFacade extends BaseFacade<OrderRequestDto, OrderResponseDto> {

    List<ProductResponseDto> getProducts(Long id);

    void addProduct(Long orderId, Long productId);

    void removeProduct(Long orderId, Long productId);
}
