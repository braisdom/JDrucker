package org.braisdom.drucker.database;

import org.braisdom.drucker.annotation.Sql;
import org.braisdom.drucker.annotation.SqlParam;
import org.braisdom.drucker.annotation.Table;

import java.util.List;

@Table(file = "/sql/abstract_table.xsql")
public interface AbstractTable<T> {

    @Sql(id = "find_by_id", sqlType = SqlType.QUERY_ONE)
    public T findById(@SqlParam("id") Integer id);

    @Sql(id = "find_all", sqlType = SqlType.QUERY_MANY)
    public List<T> findAll(@SqlParam("limit") int limit);

    public Integer deleteById(@SqlParam("id") Integer id);

    public Integer updateById(@SqlParam("id") Integer id, T object);

    public Integer update(T object);

    public T create(T object);

    public Boolean exists(Integer id);

    public Integer count();

}
