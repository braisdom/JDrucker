package org.braisdom.drucker.database;

import org.braisdom.drucker.annotation.Sql;
import org.braisdom.drucker.annotation.SqlParam;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class TableBehaviorProxy implements InvocationHandler {

    private final DatabaseSession databaseSession;

    public TableBehaviorProxy(DatabaseSession databaseSession) {
        this.databaseSession = databaseSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<? extends AbstractTable> declaringClass = (Class<? extends AbstractTable>) method.getDeclaringClass();
        Sql sql = method.getAnnotation(Sql.class);
        SqlExecuteContext sqlExecuteContext = new SqlExecuteContextImpl(sql, method.getParameters(), args);
        if(SqlType.QUERY_ONE.equals(sql.sqlType()))
            return databaseSession.executeQuery(declaringClass, sql, sqlExecuteContext);
        else if(SqlType.QUERY_MANY.equals(sql.sqlType()))
            return databaseSession.executeQueryMany(declaringClass, sql, sqlExecuteContext);
        else
            return databaseSession.executeUpdate(declaringClass, sql);
    }

    protected class SqlExecuteContextImpl implements SqlExecuteContext {

        private final Sql sql;
        private final Parameter[] parameters;
        private final Object[] parameterValues;

        public SqlExecuteContextImpl(Sql sql, Parameter[] parameters, Object[] parameterValues) {
            this.sql = sql;
            this.parameters = parameters;
            this.parameterValues = parameterValues;
        }

        @Override
        public Sql getSql() {
            return sql;
        }

        @Override
        public int getSqlParamSize() {
            return parameters.length;
        }

        @Override
        public SqlParam getSqlParam(int index) {
            return parameters[index].getAnnotation(SqlParam.class);
        }

        @Override
        public Object getSqlParamValue(int index) {
            return parameterValues[index];
        }
    }

}
