package org.braisdom.drucker.database;

import org.braisdom.drucker.annotation.Sql;
import org.braisdom.drucker.annotation.SqlParam;
import org.braisdom.drucker.annotation.Table;

import java.util.List;

@Table(file = "/sql/table_behavior.xsql", entityClass = RawEntity.class)
public interface AbstractTable<T> {

    @Sql(id = "find_by_id")
    public T findById(@SqlParam("id") Integer id);

    @Sql(id = "find_all")
    public List<T> findAll();

    public Integer deleteById(Integer id);

    public Integer truncateTable();

    public Integer updateById(Integer id, T object);

    public Integer update(T object);

    public T create(T object);

    public Boolean exists(Integer id);

    public Integer count();

}
