package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Operation;

import java.util.List;
import java.util.Set;

public interface AccountDao extends BaseDao<Account>{

    List<Operation> getOperations(Long userId);

    void addOperation(Long accountId, Long operationId);

    void removeOperation(Long accountId, Long operationId);
}
