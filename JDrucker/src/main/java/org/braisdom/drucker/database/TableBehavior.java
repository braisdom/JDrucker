package org.braisdom.drucker.database;

import org.braisdom.drucker.annotation.SQL;
import org.braisdom.drucker.annotation.SQLParam;
import org.braisdom.drucker.annotation.Table;

import java.util.List;

@Table(file = "xsql/abstract_table.xsql")
public interface TableBehavior<T> {

    @SQL(id = "find_by_id", executionType = SQLExecutionType.SELECT_ONE, primitive = true)
    T findById(@SQLParam("id") Integer id);

    @SQL(id = "find_all", executionType = SQLExecutionType.SELECT_MANY, primitive = true)
    List<T> findAll(@SQLParam("limit") int limit);

    Integer deleteById(@SQLParam("id") Integer id);

    Integer updateById(@SQLParam("id") Integer id, T object);

    Integer update(T object);

    Integer create(T object);
}
