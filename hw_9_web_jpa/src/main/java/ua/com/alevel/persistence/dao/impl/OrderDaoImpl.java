package ua.com.alevel.persistence.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import ua.com.alevel.persistence.dao.OrderDao;
import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.persistence.entity.Product;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDao {

    private final SessionFactory sessionFactory;

    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Product> getProducts(Long id) {
        return sessionFactory.getCurrentSession().find(Order.class, id).getProducts();
    }

    @Override
    public void addProduct(Long orderId, Long productId) {
        Order order = sessionFactory.getCurrentSession().find(Order.class, orderId);
        Product product = sessionFactory.getCurrentSession().find(Product.class, productId);
        order.addProduct(product);
    }

    @Override
    public void removeProduct(Long orderId, Long productId) {
        Order order = sessionFactory.getCurrentSession().find(Order.class, orderId);
        Product product = sessionFactory.getCurrentSession().find(Product.class, productId);
        order.removeProduct(product);
    }

    @Override
    public void create(Order entity) {
        sessionFactory.getCurrentSession().persist(entity);
    }

    @Override
    public void update(Order entity) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(entity);
    }

    @Override
    public void delete(Long id) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("delete from Order o where o.id = :id")
                .setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public boolean existById(Long id) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("select count(o.id) from Order o where o.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Order findById(Long id) {
        return sessionFactory.getCurrentSession().find(Order.class, id);
    }

    @Override
    public DataTableResponse<Order> findAll(DataTableRequest request) {
        int page = (request.getCurrentPage() - 1) * request.getPageSize();
        int size = request.getPageSize();
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> from = criteriaQuery.from(Order.class);
        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }

        List<Order> orders = sessionFactory.getCurrentSession()
                .createQuery(criteriaQuery)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();

        DataTableResponse<Order> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(orders);
        return tableResponse;
    }

    @Override
    public long count() {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("select count(o.id) from Order o");
        return (Long) query.getSingleResult();
    }
}
