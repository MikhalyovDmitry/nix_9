package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.AccountDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Operation;
import ua.com.alevel.service.AccountService;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final AccountDao accountDao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void create(Account account) {
        accountDao.create(account);
        LOGGER.info("Account created:" +
                " id = " + account.getId() +
                " balance = " + account.getBalance() +
                " userId = " + account.getUserId());
    }

    @Override
    public void update(Account account) {
        accountDao.update(account);
    }

    @Override
    public void delete(Long id) {
        if (accountDao.existById(id)) {
            accountDao.delete(id);
        }
    }

    @Override
    public Account findById(Long id) {
        return accountDao.findById(id);
    }

    @Override
    public DataTableResponse<Account> findAll(DataTableRequest request) {
        DataTableResponse<Account> dataTableResponse = accountDao.findAll(request);
        long count = accountDao.count();
        dataTableResponse.setItemsSize(count);
        return dataTableResponse;
    }

    @Override
    public List<Operation> getOperations(Long userId) {
        return accountDao.getOperations(userId);
    }

    @Override
    public void addOperation(Long accountId, Long operationId) {
        accountDao.addOperation(accountId, operationId);
    }

    @Override
    public void removeOperation(Long accountId, Long operationId) {
        accountDao.removeOperation(accountId, operationId);
    }
}
