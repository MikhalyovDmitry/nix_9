package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.product.ProductRequestDto;
import ua.com.alevel.dto.product.ProductResponseDto;
import ua.com.alevel.entity.Product;
import ua.com.alevel.facade.ProductFacade;
import ua.com.alevel.service.OrderService;
import ua.com.alevel.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductFacadeImpl implements ProductFacade {

    private final OrderService orderService;
    private final ProductService productService;

    public ProductFacadeImpl(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @Override
    public void create(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        product.setInStock(productRequestDto.isInStock());
        productService.create(product);
    }

    @Override
    public void update(ProductRequestDto productRequestDto, Long id) {
        Product product = productService.findById(id);
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        product.setInStock(productRequestDto.isInStock());
        productService.update(product);
    }

    @Override
    public void delete(Long id) {
        productService.delete(id);
    }

    @Override
    public ProductResponseDto findById(Long id) {
        return new ProductResponseDto(productService.findById(id));
    }

    @Override
    public List<ProductResponseDto> findAll() {
        return convertToDtoByEntity(productService.findAll());
    }

    private List<ProductResponseDto> convertToDtoByEntity(List<Product> products) {
        return products.stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }
}
