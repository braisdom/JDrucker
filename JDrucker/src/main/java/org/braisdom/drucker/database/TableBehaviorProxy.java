package org.braisdom.drucker.database;

import org.braisdom.drucker.annotation.SQL;
import org.braisdom.drucker.annotation.SQLParam;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Objects;

public class TableBehaviorProxy implements InvocationHandler {

    private final DatabaseSession databaseSession;
    private final Class<?> tableClass;

    public  TableBehaviorProxy(DatabaseSession databaseSession, Class<?> tableClass ) {
        this.databaseSession = databaseSession;
        this.tableClass = tableClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> declaringClass = method.getDeclaringClass();
        SQL sql = method.getAnnotation(SQL.class);
        SQLExecutionContext sqlExecutionContext = new SQLExecutionContextImpl(sql, method.getParameters(), args);

        if(SQLExecutionType.SELECT_ONE.equals(sql.executionType()))
            return databaseSession.executeQuery(tableClass, declaringClass, sql, sqlExecutionContext);
        else if(SQLExecutionType.SELECT_MANY.equals(sql.executionType()))
            return databaseSession.executeQueryMany(tableClass, declaringClass, sql, sqlExecutionContext);
        else
            return databaseSession.executeUpdate(tableClass, declaringClass, sql);
    }

    protected class SQLExecutionContextImpl implements SQLExecutionContext {

        private final SQL sql;
        private final Parameter[] parameters;
        private final Object[] parameterValues;

        public SQLExecutionContextImpl(SQL sql, Parameter[] parameters, Object[] parameterValues) {
            this.sql = sql;
            this.parameters = parameters;
            this.parameterValues = parameterValues;
        }

        @Override
        public SQL getSql() {
            return sql;
        }

        @Override
        public int getSqlParamSize() {
            return parameters.length;
        }

        @Override
        public SQLParam getSqlParam(int index) {
            return parameters[index].getAnnotation(SQLParam.class);
        }

        @Override
        public Object getSqlParamValue(int index) {
            return parameterValues[index];
        }
    }

}
