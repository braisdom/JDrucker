package org.braisdom.drucker.database;

import org.braisdom.drucker.annotation.SQL;
import org.braisdom.drucker.annotation.SQLParam;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class TableBehaviorProxy implements InvocationHandler {

    private final DatabaseSession databaseSession;
    private final Class<?> tableClass;

    public  TableBehaviorProxy(DatabaseSession databaseSession, Class<?> tableClass ) {
        this.databaseSession = databaseSession;
        this.tableClass = tableClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<? extends AbstractTable> declaringClass = (Class<? extends AbstractTable>) method.getDeclaringClass();
        SQL sql = method.getAnnotation(SQL.class);
        SQLExecuteContext sqlExecuteContext = new SQLExecuteContextImpl(sql, method.getParameters(), args);
        if(SQLExecutionType.SELECT_ONE.equals(sql.sqlType()))
            return databaseSession.executeQuery(declaringClass, sql, sqlExecuteContext);
        else if(SQLExecutionType.SELECT_MANY.equals(sql.sqlType()))
            return databaseSession.executeQueryMany(declaringClass, sql, sqlExecuteContext);
        else
            return databaseSession.executeUpdate(declaringClass, sql);
    }

    protected class SQLExecuteContextImpl implements SQLExecuteContext {

        private final SQL sql;
        private final Parameter[] parameters;
        private final Object[] parameterValues;

        public SQLExecuteContextImpl(SQL sql, Parameter[] parameters, Object[] parameterValues) {
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
