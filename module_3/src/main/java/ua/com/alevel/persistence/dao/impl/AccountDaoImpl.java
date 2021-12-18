package ua.com.alevel.persistence.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.dao.AccountDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Operation;
import ua.com.alevel.persistence.entity.User;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AccountDaoImpl implements AccountDao {

    private final SessionFactory sessionFactory;

    public AccountDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Account account) {
        sessionFactory.getCurrentSession().persist(account);
    }

    @Override
    public void update(Account account) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(account);
    }

    @Override
    public void delete(Long id) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("delete from Account a where a.id = :id")
                .setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public boolean existById(Long id) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("select count(a.id) from Account a where a.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;

    }

    @Override
    public Account findById(Long id) {
        return sessionFactory.getCurrentSession().find(Account.class, id);
    }

    @Override
    public DataTableResponse<Account> findAll(DataTableRequest request) {
        int page = (request.getCurrentPage() - 1) * request.getPageSize();
        int size = request.getPageSize();
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);
        Root<Account> from = criteriaQuery.from(Account.class);
        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }

        List<Account> accounts = sessionFactory.getCurrentSession()
                .createQuery(criteriaQuery)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();

        DataTableResponse<Account> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(accounts);
        return tableResponse;
    }

    @Override
    public long count() {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("select count(a.id) from Account a");
        return (Long) query.getSingleResult();
    }

    @Override
    public List<Operation> getOperations(Long accountId) {
        return sessionFactory.getCurrentSession().find(Account.class, accountId).getOperations();
    }

    @Override
    public void addOperation(Long accountId, Long operationId) {
        Account account = sessionFactory.getCurrentSession().find(Account.class, accountId);
        Operation operation = sessionFactory.getCurrentSession().find(Operation.class, operationId);
        account.addOperation(operation);
    }

    @Override
    public void removeOperation(Long accountId, Long operationId) {
        Account account = sessionFactory.getCurrentSession().find(Account.class, accountId);
        Operation operation = sessionFactory.getCurrentSession().find(Operation.class, operationId);
        account.removeOperation(operation);
    }
}
