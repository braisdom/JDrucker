package org.braisdom.drucker;

import java.util.List;

public interface TableBehavior<T> {

    public T findById(Integer id);

    public List<T> findAll();

    public void deleteById(Integer id);

    public void deleteAll();

    public void updateById(Integer id, T object);

    public T create(T object);

    public boolean exists(Integer id);

    public Integer count();

}