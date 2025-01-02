package org.karensystem.core.generic.abstracts.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAbstractService<T> {

    List<T> getAll() ;
    T getById(Long id);

    T insertItem(T entity);

    T updateItem(T entity);

    void deleteItem(Long id);

}
