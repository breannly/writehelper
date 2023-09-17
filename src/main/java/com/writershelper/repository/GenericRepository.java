package com.writershelper.repository;

import com.writershelper.model.Writer;

import java.io.IOException;

public interface GenericRepository<T, ID> {

    T save(T t);

    T get(ID id);

}
