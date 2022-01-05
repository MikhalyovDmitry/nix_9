package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.persistence.entity.Product;
import ua.com.alevel.persistence.repository.ProductRepository;
import ua.com.alevel.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final CrudRepositoryHelper<Product, ProductRepository> repositoryHelper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(CrudRepositoryHelper<Product, ProductRepository> repositoryHelper, ProductRepository productRepository) {
        this.repositoryHelper = repositoryHelper;
        this.productRepository = productRepository;
    }

    @Override
    public List<Order> getOrders(Long id) {
        return repositoryHelper.findById(productRepository, id).get().getOrders();
    }

    @Override
    public void create(Product entity) {
       repositoryHelper.create(productRepository, entity);
    }

    @Override
    public void update(Product entity) {
        repositoryHelper.update(productRepository, entity);    }

    @Override
    public void delete(Long id) {
        repositoryHelper.delete(productRepository, id);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repositoryHelper.findById(productRepository, id);
    }

    @Override
    public DataTableResponse<Product> findAll(DataTableRequest request) {
        return repositoryHelper.findAll(productRepository, request);
    }
}
