package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.OrderProductDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.persistence.entity.OrderProduct;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.com.alevel.persistence.dao.query.JpaQueryUtil.*;

@Service
public class OrderProductDaoImpl implements OrderProductDao {

    private final JpaConfig jpaConfig;

    public OrderProductDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(OrderProduct orderProduct) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_ORDER_PRODUCT_QUERY)) {
            preparedStatement.setLong(1, orderProduct.getOrderId());
            preparedStatement.setLong(2, orderProduct.getProductId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void update(OrderProduct orderProduct) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(UPDATE_ORDER_PRODUCT_BY_ID_QUERY + orderProduct.getId())) {
            preparedStatement.setLong(1, orderProduct.getOrderId());
            preparedStatement.setLong(2, orderProduct.getProductId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_ORDER_PRODUCT_BY_ID_QUERY + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        long count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(EXIST_ORDER_PRODUCT_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                count = resultSet.getLong("COUNT(*)");
                //System.out.println("count = " + count + "order_product with id = " + id + " doesn't exist");

            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return count == 1;
    }

    @Override
    public OrderProduct findById(Long id) {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ORDER_PRODUCT_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                return initOrderProductByResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<OrderProduct> findByOrderId(Long orderId) {
        List<OrderProduct> orderProducts = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_PRODUCTS_BY_ORDER_ID_QUERY + orderId)) {
            while (resultSet.next()) {
                orderProducts.add(initOrderProductByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return orderProducts;
    }

    @Override
    public List<OrderProduct> findByProductId(Long productId) {
        List<OrderProduct> orderProducts = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_ORDERS_BY_PRODUCT_ID_QUERY + productId)) {
            while (resultSet.next()) {
                orderProducts.add(initOrderProductByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return orderProducts;
    }


//    @Override
//    public List<OrderProduct> findAll() {
//        List<OrderProduct> orderProducts = new ArrayList<>();
//        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_ORDER_PRODUCTS_QUERY)) {
//            while (resultSet.next()) {
//                orderProducts.add(initOrderProductByResultSet(resultSet));
//            }
//        } catch (SQLException e) {
//            System.out.println("problem: = " + e.getMessage());
//        }
//        return orderProducts;
//    }

    private OrderProduct initOrderProductByResultSet(ResultSet resultSet) throws SQLException {
        OrderProduct orderProduct = new OrderProduct();
        long id = resultSet.getLong("id");
        long orderId = resultSet.getLong("order_id");
        long productId = resultSet.getLong("product_id");
        orderProduct.setId(id);
        orderProduct.setOrderId(orderId);
        orderProduct.setProductId(productId);
        return orderProduct;
    }

    public OrderProduct findByOrderIdAndProductId(Long productId, Long orderId) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(FIND_ORDER_PRODUCT_BY_PRODUCT_ID_AND_ORDER_ID_QUERY)) {
            preparedStatement.setLong(1, productId);
            preparedStatement.setLong(2, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return initOrderProductByResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return null;
    }

    @Override
    public DataTableResponse<OrderProduct> findAll(DataTableRequest request) {
        List<OrderProduct> orderProducts = new ArrayList<>();

        int limit = (request.getCurrentPage() - 1) * request.getPageSize();

        String sql = "select id, order_id, product_id from products order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();

        System.out.println("sql = " + sql);

        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                OrderProductResultSet orderProductResultSet = convertResultSetToOrderProduct(resultSet);
                orderProducts.add(orderProductResultSet.getOrderProduct());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DataTableResponse<OrderProduct> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(orderProducts);
        return tableResponse;
    }

    @Override
    public long count() {
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery("select count(*) as count from authors")) {
            while (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private OrderProductResultSet convertResultSetToOrderProduct(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Long orderId = resultSet.getLong("order_id");
        Long productId = resultSet.getLong("product_id");

        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setId(id);
        orderProduct.setOrderId(orderId);
        orderProduct.setProductId(productId);
        return new OrderProductResultSet(orderProduct);
    }

    private static class OrderProductResultSet {

        private final OrderProduct orderProduct;

        private OrderProductResultSet(OrderProduct orderProduct) {
            this.orderProduct = orderProduct;
        }

        public OrderProduct getOrderProduct() {
            return orderProduct;
        }

    }


}
