package ua.com.alevel.service;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.BaseEntity;

import java.util.List;

public interface BaseService<E extends BaseEntity> {

    void create(E e);

    void update(E e);

    void delete(Long id);

    E findById(Long id);

    DataTableResponse<E> findAll(DataTableRequest request);
}
