package com.writershelper.repository;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T, ID> {

    T save(T t);

    T update(T t);

    List<T> getAll();

    Optional<T> get(ID id);

}
