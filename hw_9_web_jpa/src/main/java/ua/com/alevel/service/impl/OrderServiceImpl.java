package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.OrderDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.persistence.entity.Product;
import ua.com.alevel.service.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    public OrderServiceImpl(
            OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public void addProduct(Long orderId, Long productId) {
        orderDao.addProduct(orderId, productId);
    }

    @Override
    public void removeProduct(Long orderId, Long productId) {
        orderDao.removeProduct(orderId, productId);
    }

    @Override
    public List<Product> getProducts(Long id) {
        return orderDao.getProducts(id);
    }

    @Override
    public void create(Order order) {
        orderDao.create(order);
    }

    @Override
    public void update(Order order) {
        orderDao.update(order);
    }

    @Override
    public void delete(Long id) {
        if (orderDao.existById(id)) {
            orderDao.delete(id);
        }
    }

    @Override
    public Order findById(Long id) {
        return orderDao.findById(id);
    }

    @Override
    public DataTableResponse<Order> findAll(DataTableRequest request) {
        DataTableResponse<Order> dataTableResponse = orderDao.findAll(request);
        long count = orderDao.count();
        dataTableResponse.setItemsSize(count);
        return dataTableResponse;
    }
}
