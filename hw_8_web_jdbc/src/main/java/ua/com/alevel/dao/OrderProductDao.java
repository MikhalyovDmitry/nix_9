package ua.com.alevel.dao;

import ua.com.alevel.entity.OrderProduct;

import java.util.List;

public interface OrderProductDao extends BaseDao<OrderProduct> {

    List<OrderProduct> findByOrderId(Long orderId);

    List<OrderProduct> findByProductId(Long productId);

    OrderProduct findByOrderIdAndProductId(Long productId, Long orderId);
}
