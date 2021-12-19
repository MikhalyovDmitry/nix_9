package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.UserDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(Long id) {
        if (userDao.existById(id)) {
            userDao.delete(id);
        }
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public DataTableResponse<User> findAll(DataTableRequest request) {
        DataTableResponse<User> dataTableResponse = userDao.findAll(request);
        long count = userDao.count();
        dataTableResponse.setItemsSize(count);
        return dataTableResponse;
    }

    @Override
    public List<Account> getAccounts(Long userId) {
        return userDao.getAccounts(userId);
    }

    @Override
    public void addAccount(Long userId, Long accountId) {
        userDao.addAccount(userId, accountId);
    }

    @Override
    public void removeAccount(Long userId, Long accountId) {
        userDao.removeAccount(userId, accountId);
        LOGGER.info("Account with id = " + accountId + " deleted for user with id = " + userId);
    }
}
