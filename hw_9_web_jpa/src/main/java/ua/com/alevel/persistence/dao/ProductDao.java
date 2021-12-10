package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.persistence.entity.Product;

import java.util.List;

public interface ProductDao extends BaseDao<Product> {

    List<Order> getOrders(Long id);

}
