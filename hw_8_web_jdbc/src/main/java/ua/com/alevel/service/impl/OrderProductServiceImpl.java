package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.OrderProductDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.OrderProduct;
import ua.com.alevel.service.OrderProductService;

import java.util.List;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    private final OrderProductDao orderProductDao;

    public OrderProductServiceImpl(
            OrderProductDao orderProductDao) {
        this.orderProductDao = orderProductDao;
    }

    @Override
    public void create(OrderProduct orderProduct) {
        orderProductDao.create(orderProduct);
    }

    @Override
    public void update(OrderProduct orderProduct) {
        orderProductDao.update(orderProduct);
    }

    @Override
    public void delete(Long id) {
        if (orderProductDao.existById(id)) {
            orderProductDao.delete(id);
        }
    }

    @Override
    public OrderProduct findById(Long id) {
        return orderProductDao.findById(id);
    }

    @Override
    public List<OrderProduct> findByOrderId(Long orderId) {
        return orderProductDao.findByOrderId(orderId);
    }

    @Override
    public List<OrderProduct> findByProductId(Long productId) {
        return orderProductDao.findByProductId(productId);
    }

//    @Override
//    public List<OrderProduct> findAll() {
//        return orderProductDao.findAll();
//    }

    @Override
    public OrderProduct findByOrderIdAndProductId(Long productId, Long orderId) {
        return orderProductDao.findByOrderIdAndProductId(productId, orderId);
    }

    @Override
    public DataTableResponse<OrderProduct> findAll(DataTableRequest request) {
        DataTableResponse<OrderProduct> dataTableResponse = orderProductDao.findAll(request);
        long count = orderProductDao.count();
        dataTableResponse.setItemsSize(count);
        return dataTableResponse;
    }
}
