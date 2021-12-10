package ua.com.alevel.persistence.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;

import javax.persistence.OptimisticLockException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import ua.com.alevel.persistence.dao.ProductDao;
import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.persistence.entity.Product;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

    private final SessionFactory sessionFactory;

    public ProductDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Order> getOrders(Long id) {
        return sessionFactory.getCurrentSession().find(Product.class, id).getOrders();
    }

    @Override
    public void create(Product entity) {
        sessionFactory.getCurrentSession().persist(entity);
    }

    @Override
    public void update(Product entity) {
        sessionFactory.getCurrentSession().merge(entity);
    }

    @Override
    public void delete(Long id) {
        int isSuccessful = sessionFactory.getCurrentSession().createQuery("delete from Product p where p.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        if (isSuccessful == 0) {
            throw new OptimisticLockException("product modified concurrently");
        }
    }

    @Override
    public boolean existById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("select count(p.id) from Product p where p.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Product findById(Long id) {
        return sessionFactory.getCurrentSession().find(Product.class, id);
    }

    @Override
    public DataTableResponse<Product> findAll(DataTableRequest request) {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> from = criteriaQuery.from(Product.class);
        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }

        int page = (request.getCurrentPage() - 1) * request.getPageSize();
        int size = page + request.getPageSize();

        List<Product> products = sessionFactory.getCurrentSession().createQuery(criteriaQuery)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();

        DataTableResponse<Product> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(products);
        return tableResponse;
    }

    @Override
    public long count() {
        Query query = sessionFactory.getCurrentSession().createQuery("select count(p.id) from Product p");
        return (Long) query.getSingleResult();
    }
}
