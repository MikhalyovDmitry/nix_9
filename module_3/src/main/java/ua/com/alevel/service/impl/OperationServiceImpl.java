package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.OperationDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Operation;
import ua.com.alevel.service.OperationService;

@Service
public class OperationServiceImpl implements OperationService {

    private final OperationDao operationDao;

    public OperationServiceImpl(OperationDao operationDao) {
        this.operationDao = operationDao;
    }

    @Override
    public void create(Operation operation) {
        operationDao.create(operation);
    }

    @Override
    public void update(Operation operation) {
        operationDao.update(operation);
    }

    @Override
    public void delete(Long id) {
        if (operationDao.existById(id)) {
            operationDao.delete(id);
        }
    }

    @Override
    public Operation findById(Long id) {
        return operationDao.findById(id);
    }

    @Override
    public DataTableResponse<Operation> findAll(DataTableRequest request) {
        DataTableResponse<Operation> dataTableResponse = operationDao.findAll(request);
        long count = operationDao.count();
        dataTableResponse.setItemsSize(count);
        return dataTableResponse;
    }
}
