package org.braisdom.drucker.database;

import org.braisdom.drucker.annotation.SQL;
import org.braisdom.drucker.annotation.SQLParam;
import org.braisdom.drucker.annotation.Table;

import java.util.List;

@Table(file = "/sql/abstract_table.xsql")
public interface AbstractTable<T> {

    @SQL(id = "find_by_id", sqlType = SQLExecutionType.SELECT_ONE)
    public T findById(@SQLParam("id") Integer id);

    @SQL(id = "find_all", sqlType = SQLExecutionType.SELECT_MANY)
    public List<T> findAll(@SQLParam("limit") int limit);

    public Integer deleteById(@SQLParam("id") Integer id);

    public Integer updateById(@SQLParam("id") Integer id, T object);

    public Integer update(T object);

    public T create(T object);

    public Boolean exists(Integer id);

    public Integer count();

}
