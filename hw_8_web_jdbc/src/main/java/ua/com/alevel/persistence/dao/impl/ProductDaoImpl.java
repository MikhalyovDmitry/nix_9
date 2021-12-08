package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.ProductDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.com.alevel.persistence.dao.query.JpaQueryUtil.*;

@Service
public class ProductDaoImpl implements ProductDao {

    private final JpaConfig jpaConfig;

    public ProductDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Product product) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_PRODUCT_QUERY)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setBoolean(3, product.isInStock());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void update(Product product) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(UPDATE_PRODUCT_BY_ID_QUERY + product.getId())) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setBoolean(3, product.isInStock());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_PRODUCT_BY_ID_QUERY + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        long count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(EXIST_PRODUCT_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                count = resultSet.getLong("COUNT(*)");
                System.out.println("count = " + count);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return count == 1;
    }

    @Override
    public Product findById(Long id) {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_PRODUCT_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                return initProductByResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return null;
    }

//    @Override
//    public List<Product> findAll() {
//        List<Product> products = new ArrayList<>();
//        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_PRODUCTS_QUERY)) {
//            while (resultSet.next()) {
//                products.add(initEmployeeByResultSet(resultSet));
//            }
//        } catch (SQLException e) {
//            System.out.println("problem: = " + e.getMessage());
//        }
//        return products;
//    }

    private Product initProductByResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        double price = resultSet.getDouble("price");
        boolean inStock = resultSet.getBoolean("instock");
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setInStock(inStock);
        return product;
    }

    @Override
    public DataTableResponse<Product> findAll(DataTableRequest request) {
        List<Product> products = new ArrayList<>();

        int limit = (request.getCurrentPage() - 1) * request.getPageSize();

        String sql = "select id, name, price, instock from products order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();

        System.out.println("sql = " + sql);

        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                ProductResultSet productResultSet = convertResultSetToProduct(resultSet);
                products.add(productResultSet.getProduct());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DataTableResponse<Product> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(products);
        return tableResponse;
    }

    @Override
    public long count() {
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery("select count(*) as count from products")) {
            while (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private ProductResultSet convertResultSetToProduct(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        double price = resultSet.getDouble("price");
        Boolean inStock = resultSet.getBoolean("inStock");

        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setInStock(inStock);

        return new ProductResultSet(product);
    }

    private static class ProductResultSet {

        private final Product product;

        private ProductResultSet(Product product) {
            this.product = product;
        }

        public Product getProduct() {
            return product;
        }

    }
}
