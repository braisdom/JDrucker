package com.braisdom.jdrucker;

import java.util.List;

public interface Table<T> {

    public T findById(Integer id);

    public List<T> findAll();


}
