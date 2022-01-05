package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.persistence.entity.Product;

import java.util.List;

public interface ProductService extends BaseService<Product> {

    List<Order> getOrders(Long id);
}
