package com.arstansubanov.cinematica.services;

import java.util.List;

public interface BaseService<T> {
    List<T> findAll();
    T findById(int id);
    void save(T t);
    void delete(int id);
    void update(int id, T t);
}
