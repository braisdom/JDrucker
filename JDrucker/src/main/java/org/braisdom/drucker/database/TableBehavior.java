package org.braisdom.drucker.database;

import org.braisdom.drucker.annotation.Sql;
import org.braisdom.drucker.annotation.Table;

import java.util.List;

@Table(file = "/sql/table_behavior.xsql", model = RawModel.class)
public interface TableBehavior<T> {

    @Sql("find_by_id")
    public T findById(Integer id);

    @Sql("find_all")
    public List<T> findAll();

    public Integer deleteById(Integer id);

    public Integer truncateTable();

    public Integer updateById(Integer id, T object);

    public Integer update(T object);

    public T create(T object);

    public Boolean exists(Integer id);

    public Integer count();

}
