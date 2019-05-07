package org.braisdom.drucker.database;

import java.util.List;

public interface TableBehavior<T> {

    public static final String SQL_FILE_NAME = "/sql/table_behavior.xsql";

    public T findById(Integer id);

    public List<T> findAll();

    public Integer deleteById(Integer id);

    public Integer truncateTable();

    public Integer updateById(Integer id, T object);

    public Integer update(T object);

    public T create(T object);

    public Boolean exists(Integer id);

    public Integer count();

}
