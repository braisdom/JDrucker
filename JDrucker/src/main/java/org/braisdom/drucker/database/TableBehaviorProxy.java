package org.braisdom.drucker.database;

import org.braisdom.drucker.annotation.SQL;
import org.braisdom.drucker.annotation.SQLParam;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class TableBehaviorProxy implements InvocationHandler {

    private final DatabaseSession databaseSession;
    private final Class<?> tableClass;

    public TableBehaviorProxy(DatabaseSession databaseSession, Class<?> tableClass) {
        this.databaseSession = databaseSession;
        this.tableClass = tableClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> declaringClass = method.getDeclaringClass();
        SQL sql = method.getAnnotation(SQL.class);
        if(sql == null)
            throw new IllegalSQLTypeException("The SQL annotation is required on method: " + method.getName());
        SQLParameter[] sqlParameters = createParameters(method.getParameters(), args);

        if (SQLExecutionType.SELECT_ONE.equals(sql.executionType()))
            return databaseSession.executeQuery(tableClass, declaringClass, sql, sqlParameters);
        else if (SQLExecutionType.SELECT_MANY.equals(sql.executionType()))
            return databaseSession.executeQueryMany(tableClass, declaringClass, sql, sqlParameters);
        else
            return databaseSession.executeUpdate(tableClass, declaringClass, sql, sqlParameters);
    }

    private SQLParameter[] createParameters(Parameter[] rawParameters, Object[] values) {
        List<SQLParameter> sqlParameters = new ArrayList<>();
        for (int i = 0; i < rawParameters.length; i++) {
            sqlParameters.add(new SQLParameterImpl(rawParameters[i], values[i]));
        }
        return sqlParameters.toArray(new SQLParameter[]{});
    }

    private class SQLParameterImpl implements SQLParameter {

        private final Parameter parameter;
        private final Object value;

        public SQLParameterImpl(Parameter parameter, Object value) {
            this.parameter = parameter;
            this.value = value;
        }

        @Override
        public SQLParam getParam() {
            return parameter.getAnnotation(SQLParam.class);
        }

        @Override
        public Object getValue() {
            return value;
        }
    }
}
