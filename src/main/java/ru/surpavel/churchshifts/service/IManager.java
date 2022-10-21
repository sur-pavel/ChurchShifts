package ru.surpavel.churchshifts.service;

import java.util.List;
import java.util.Optional;

public interface IManager<T> {

    T save(T entity);

    Optional<T> findById(Long id);

    boolean existsById(Long id);

    List<T> findAll();

    void delete(T entity);
}
