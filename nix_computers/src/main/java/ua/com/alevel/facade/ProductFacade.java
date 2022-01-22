package ua.com.alevel.facade;

import ua.com.alevel.persistence.entity.Product;
import ua.com.alevel.view.dto.request.ProductRequestDto;
import ua.com.alevel.view.dto.response.OrderResponseDto;
import ua.com.alevel.view.dto.response.ProductResponseDto;

import java.util.List;

public interface ProductFacade extends BaseFacade<ProductRequestDto, ProductResponseDto> {

    Product findProductById(Long productId);

    List<OrderResponseDto> getOrders(Long id);

}
