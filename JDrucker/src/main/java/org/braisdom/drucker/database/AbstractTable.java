package org.braisdom.drucker.database;

import org.braisdom.drucker.annotation.Primitive;
import org.braisdom.drucker.annotation.SQL;
import org.braisdom.drucker.annotation.SQLParam;
import org.braisdom.drucker.annotation.Table;

import java.util.List;

@Table(file = "/xsql/abstract_table.xsql")
public interface AbstractTable<T> {

    @Primitive
    @SQL(id = "find_by_id", executionType = SQLExecutionType.SELECT_ONE)
    T findById(@SQLParam("id") Integer id);

    @Primitive
    @SQL(id = "find_all", executionType = SQLExecutionType.SELECT_MANY)
    List<T> findAll(@SQLParam("limit") int limit);

    @Primitive
    Integer deleteById(@SQLParam("id") Integer id);

    @Primitive
    Integer updateById(@SQLParam("id") Integer id, T object);

    @Primitive
    Integer update(T object);

    @Primitive
    Integer create(T object);

    @Primitive
    Boolean exists(Integer id);

    @Primitive
    Integer count();

}
