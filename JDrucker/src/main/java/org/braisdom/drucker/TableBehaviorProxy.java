package org.braisdom.drucker;

import javax.sql.DataSource;
import java.util.List;

public class TableBehaviorProxy implements TableBehavior {

    private final DataSource dataSource;
    private final TableDescriptor tableDescriptor;

    public TableBehaviorProxy(DataSource dataSource, TableDescriptor tableDescriptor) {
        this.dataSource = dataSource;
        this.tableDescriptor = tableDescriptor;
    }

    @Override
    public Object findById(Integer id) {
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Integer deleteById(Integer id) {
        return null;
    }

    @Override
    public Integer truncateTable() {
        return null;
    }

    @Override
    public Integer updateById(Integer id, Object object) {
        return null;
    }

    @Override
    public Integer update(Object object) {
        return null;
    }

    @Override
    public Object create(Object object) {
        return null;
    }

    @Override
    public Boolean exists(Integer id) {
        return null;
    }

    @Override
    public Integer count() {
        return null;
    }
}
