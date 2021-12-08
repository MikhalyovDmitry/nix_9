package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.ProductDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Product;
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

//    @Override
//    public List<Product> findAll() {
//        return productDao.findAll();
//    }

    @Override
    public DataTableResponse<Product> findAll(DataTableRequest request) {
        DataTableResponse<Product> dataTableResponse = productDao.findAll(request);
        long count = productDao.count();
        dataTableResponse.setItemsSize(count);
        return dataTableResponse;
    }


}
