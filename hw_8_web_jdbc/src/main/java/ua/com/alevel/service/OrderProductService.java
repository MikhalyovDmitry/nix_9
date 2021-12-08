package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.OrderProduct;

import java.util.List;

public interface OrderProductService extends BaseService<OrderProduct> {

    List<OrderProduct> findByOrderId(Long orderId);

    List<OrderProduct> findByProductId(Long productId);

    OrderProduct findByOrderIdAndProductId(Long productId, Long orderId);
}
