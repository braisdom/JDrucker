package org.braisdom.drucker.database;

import org.braisdom.drucker.WordUtil;
import org.braisdom.drucker.annotation.Sql;
import org.braisdom.drucker.annotation.Table;

import java.lang.reflect.Method;

public final class TableDescriptor {

    private final Table tableAnnotation;

    public TableDescriptor(Class<? extends AbstractTable> tableClass) {
        this.tableAnnotation = tableClass.getAnnotation(Table.class);
        if(this.tableAnnotation == null)
            throw new IllegalArgumentException("Class " + tableClass.getName() + " has no AbstractTable annotation.");
    }

    public String getTableName() {
        String rawTableName = tableAnnotation.tableName();
        if(rawTableName == null || rawTableName.length() == 0)
            return WordUtil.tableize(tableAnnotation.entityClass().getSimpleName());
        return rawTableName;
    }

    public Class getModelClass() {
        Class rawClass = tableAnnotation.entityClass();
        if(rawClass == null)
            return RawEntity.class;
        return rawClass;
    }

    public String getSqlFileName() {
        String rawSqlFileName = tableAnnotation.file();
        if(rawSqlFileName == null || rawSqlFileName.length() == 0) {
            Class modelClass = getModelClass();
            return "/sql/" + WordUtil.tableize(modelClass.getSimpleName());
        }
        return rawSqlFileName;
    }

    public String getSqlId(Method method) {
        Sql sql = method.getAnnotation(Sql.class);
        if(sql == null || sql.id().length() == 0)
            return WordUtil.tableize(method.getName());
        return sql.id();
    }

    public boolean isUniqued() {
        return tableAnnotation.uniqued();
    }
}
