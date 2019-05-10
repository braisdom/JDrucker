package org.braisdom.drucker.database;

import org.braisdom.drucker.WordUtil;
import org.braisdom.drucker.annotation.Sql;
import org.braisdom.drucker.annotation.TableBehavior;

import java.lang.reflect.Method;

public final class TableDescriptor {

    private final org.braisdom.drucker.annotation.TableBehavior tableBehaviorAnnotation;

    public TableDescriptor(Class<? extends org.braisdom.drucker.database.TableBehavior> tableClass) {
        this.tableBehaviorAnnotation = tableClass.getAnnotation(TableBehavior.class);
        if(this.tableBehaviorAnnotation == null)
            throw new IllegalArgumentException("Class " + tableClass.getName() + " has no TableBehavior annotation.");
    }

    public String getTableName() {
        String rawTableName = tableBehaviorAnnotation.tableName();
        if(rawTableName == null || rawTableName.length() == 0)
            return WordUtil.tableize(tableBehaviorAnnotation.beanClass().getSimpleName());
        return rawTableName;
    }

    public Class getModelClass() {
        Class rawClass = tableBehaviorAnnotation.beanClass();
        if(rawClass == null)
            return RawBean.class;
        return rawClass;
    }

    public String getSqlFileName() {
        String rawSqlFileName = tableBehaviorAnnotation.file();
        if(rawSqlFileName == null || rawSqlFileName.length() == 0) {
            Class modelClass = getModelClass();
            return "/sql/" + WordUtil.tableize(modelClass.getSimpleName());
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
        return tableBehaviorAnnotation.uniqued();
    }
}
