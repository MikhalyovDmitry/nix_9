package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.dao.OrderDao;
import ua.com.alevel.entity.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static ua.com.alevel.dao.query.JpaQueryUtil.*;

@Service
public class OrderDaoImpl implements OrderDao {

    private final JpaConfig jpaConfig;

    public OrderDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Order order) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_ORDER_QUERY)) {
            preparedStatement.setString(1, order.getCustomer());
            preparedStatement.setBoolean(2, order.isDelivered());
            preparedStatement.setTimestamp(3, new Timestamp(order.getCreated().getTime()));
            preparedStatement.setTimestamp(4, new Timestamp(order.getUpdated().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void update(Order order) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(UPDATE_BY_ID_QUERY + order.getId())) {
            preparedStatement.setString(1, order.getCustomer());
            preparedStatement.setBoolean(2, order.isDelivered());
            preparedStatement.setTimestamp(3, new Timestamp(order.getUpdated().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_ORDER_BY_ID_QUERY + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        long count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(EXIST_ORDER_BY_ID_QUERY + id)) {
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
    public Order findById(Long id) {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ORDER_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                return initCustomerByResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_ORDERS_QUERY)) {
            while (resultSet.next()) {
                orders.add(initCustomerByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return orders;
    }

    private Order initCustomerByResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String customer = resultSet.getString("customer");
        boolean delivered = resultSet.getBoolean("delivered");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        Order order = new Order();
        order.setId(id);
        order.setCustomer(customer);
        order.setDelivered(delivered);
        order.setCreated(created);
        order.setUpdated(updated);
        return order;
    }
}
