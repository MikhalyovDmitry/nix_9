package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dao.OrderDao;
import ua.com.alevel.entity.Order;
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
    public List<Order> findAll() {
        return orderDao.findAll();
    }
}
