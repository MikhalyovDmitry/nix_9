package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.BaseEntity;

import java.util.List;

public interface BaseDao<E extends BaseEntity> {

    void create(E e);

    void update(E e);

    void delete(Long id);

    boolean existById(Long id);

    E findById(Long id);

    DataTableResponse<E> findAll(DataTableRequest request);

    long count();

}
