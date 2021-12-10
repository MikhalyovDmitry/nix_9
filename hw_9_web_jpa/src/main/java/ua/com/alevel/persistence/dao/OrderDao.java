package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.persistence.entity.Product;

import java.util.List;

public interface OrderDao extends BaseDao<Order> {

    List<Product> getProducts(Long id);

    void addProduct(Long orderId, Long productId);

    void removeProduct(Long orderId, Long productId);

}
