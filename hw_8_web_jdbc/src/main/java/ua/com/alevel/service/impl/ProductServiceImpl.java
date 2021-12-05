package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dao.ProductDao;
import ua.com.alevel.entity.Product;
import ua.com.alevel.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void create(Product product) {
        productDao.create(product);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public void delete(Long id) {
        productDao.delete(id);
    }

    @Override
    public Product findById(Long id) {
        return productDao.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

//    @Override
//    public List<Product> findAllByDepartmentId(Long departmentId) {
//        return productDao.findAllByDepartmentId(departmentId);
//    }
}
