package org.braisdom.drucker.database;

import org.braisdom.drucker.WordUtil;
import org.braisdom.drucker.annotation.Sql;
import org.braisdom.drucker.annotation.Table;

import java.lang.reflect.Method;

public final class TableDescriptor {

    private final Table tableAnnotation;
    private final Class tableClass;

    public TableDescriptor(Class tableClass, Table tableAnnotation) {
        this.tableClass = tableClass;
        this.tableAnnotation = tableAnnotation;
    }

    public String getTableName() {
        String rawTableName = tableAnnotation.name();
        if(rawTableName == null || rawTableName.length() == 0)
            return WordUtil.tableize(tableAnnotation.model().getSimpleName());
        return rawTableName;
    }

    public Class getModelClass() {
        Class rawClass = tableAnnotation.model();
        if(rawClass == null)
            return RawModel.class;
        return rawClass;
    }

    public String getSqlFileName() {
        String rawSqlFileName = tableAnnotation.file();
        if(rawSqlFileName == null || rawSqlFileName.length() == 0) {
            Class modelClass = getModelClass();
            if(modelClass.equals(RawModel.class))
                return TableBehavior.SQL_FILE_NAME;
            return WordUtil.tableize(modelClass.getSimpleName());
        }
        return rawSqlFileName;
    }

    public String getSqlId(Method method) {
        Sql sql = method.getAnnotation(Sql.class);
        if(sql == null || sql.value().length() == 0)
            return WordUtil.tableize(method.getName());
        return sql.value();
    }

    public boolean isUniqued() {
        return tableAnnotation.uniqued();
    }
}
