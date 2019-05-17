package org.braisdom.drucker;

import org.braisdom.drucker.annotation.Primitive;
import org.braisdom.drucker.annotation.SQL;
import org.braisdom.drucker.database.AbstractTable;
import org.braisdom.drucker.database.SQLExecutionType;

import java.util.List;

public abstract class ActiveRecord implements AbstractTable {

    @Override
    @Primitive
    @SQL(id = "find_by_id", executionType = SQLExecutionType.SELECT_ONE)
    public Object findById(Integer id) {
        return null;
    }

    @Override
    @Primitive
    public List findAll(int limit) {
        return null;
    }

    @Override
    @Primitive
    public Integer deleteById(Integer id) {
        return null;
    }

    @Override
    @Primitive
    public Integer updateById(Integer id, Object object) {
        return null;
    }

    @Override
    @Primitive
    public Integer update(Object object) {
        return null;
    }

    @Override
    @Primitive
    public Object create(Object object) {
        return null;
    }

    public Boolean exists(Integer id) {
        return null;
    }

    public Integer count() {
        return null;
    }
}
