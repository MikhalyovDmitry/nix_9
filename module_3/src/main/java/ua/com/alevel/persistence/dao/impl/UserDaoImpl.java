package ua.com.alevel.persistence.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.UserDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(User user) {
        sessionFactory.getCurrentSession().persist(user);
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(user);
    }

    @Override
    public void delete(Long id) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("delete from User u where u.id = :id")
                .setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public boolean existById(Long id) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("select count(u.id) from User u where u.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public User findById(Long id) {
        return sessionFactory.getCurrentSession().find(User.class, id);
    }

    @Override
    public DataTableResponse<User> findAll(DataTableRequest request) {
        int page = (request.getCurrentPage() - 1) * request.getPageSize();
        int size = request.getPageSize();
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> from = criteriaQuery.from(User.class);
        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }

        List<User> users = sessionFactory.getCurrentSession()
                .createQuery(criteriaQuery)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();

        DataTableResponse<User> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(users);
        return tableResponse;    }

    @Override
    public long count() {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("select count(u.id) from User u");
        return (Long) query.getSingleResult();
    }

    @Override
    public List<Account> getAccounts(Long userId) {
        return sessionFactory.getCurrentSession().find(User.class, userId).getAccounts();
    }

    @Override
    public void addAccount(Long userId, Long accountId) {
        User user = sessionFactory.getCurrentSession().find(User.class, userId);
        Account account = sessionFactory.getCurrentSession().find(Account.class, accountId);
        user.addAccount(account);
    }

    @Override
    public void removeAccount(Long userId, Long accountId) {
        User user = sessionFactory.getCurrentSession().find(User.class, userId);
        Account account = sessionFactory.getCurrentSession().find(Account.class, accountId);
        user.removeAccount(account);
    }
}
