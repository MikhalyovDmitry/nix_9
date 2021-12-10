package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.persistence.entity.Product;

import java.util.List;

public interface OrderService extends BaseService<Order> {

    List<Product> getProducts(Long id);

    void addProduct(Long orderId, Long productId);

    void removeProduct(Long orderId, Long productId);
}
