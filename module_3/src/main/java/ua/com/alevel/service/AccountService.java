package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Operation;

import java.util.List;

public interface AccountService extends BaseService<Account> {

    List<Operation> getOperations(Long userId);

    void addOperation(Long accountId, Long operationId);

    void removeOperation(Long accountId, Long operationId);

}
