package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;

import java.util.List;

public interface UserService extends BaseService<User> {

    List<Account> getAccounts(Long userId);

    void addAccount(Long userId, Long accountId);

    void removeAccount(Long userId, Long accountId);

}
