package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.persistence.entity.Product;
import ua.com.alevel.persistence.repository.OrderRepository;
import ua.com.alevel.persistence.repository.ProductRepository;
import ua.com.alevel.service.OrderService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final CrudRepositoryHelper<Order, OrderRepository> orderRepositoryHelper;
    private final CrudRepositoryHelper<Product, ProductRepository> productRepositoryHelper;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(CrudRepositoryHelper<Order, OrderRepository> orderRepositoryHelper,
                            CrudRepositoryHelper<Product, ProductRepository> productRepositoryHelper,
                            OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepositoryHelper = orderRepositoryHelper;
        this.productRepositoryHelper = productRepositoryHelper;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void addProduct(Long orderId, Long productId) {
        Order order = orderRepositoryHelper.findById(orderRepository, orderId).get();
        Product product = productRepositoryHelper.findById(productRepository, productId).get();
        order.addProduct(product);
        orderRepositoryHelper.update(orderRepository, order);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void removeProduct(Long orderId, Long productId) {
        Order order = orderRepositoryHelper.findById(orderRepository, orderId).get();
        Product product = productRepositoryHelper.findById(productRepository, productId).get();
        order.removeProduct(product);
        orderRepositoryHelper.update(orderRepository, order);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<Product> getProducts(Long id) {
        return orderRepositoryHelper.findById(orderRepository, id).get().getProducts();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void create(Order entity) {
        orderRepositoryHelper.create(orderRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(Order entity) {
        orderRepositoryHelper.update(orderRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void delete(Long id) {
        orderRepositoryHelper.delete(orderRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Order> findById(Long id) {
        return orderRepositoryHelper.findById(orderRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Order> findAll(DataTableRequest request) {
        return orderRepositoryHelper.findAll(orderRepository, request);
    }
}
