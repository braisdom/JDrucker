package org.braisdom.drucker.database;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.braisdom.drucker.annotation.SQL;
import org.braisdom.drucker.annotation.SQLParam;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class TableBehaviorProxy implements MethodInterceptor {

    private final DatabaseSession databaseSession;
    private final Class<?> tableClass;

    public TableBehaviorProxy(DatabaseSession databaseSession, Class<?> tableClass) {
        this.databaseSession = databaseSession;
        this.tableClass = tableClass;
    }

    private SQLParameter[] createParameters(Parameter[] rawParameters, Object[] values) {
        List<SQLParameter> sqlParameters = new ArrayList<>();
        for (int i = 0; i < rawParameters.length; i++) {
            sqlParameters.add(new SQLParameterImpl(rawParameters[i], values[i]));
        }
        return sqlParameters.toArray(new SQLParameter[]{});
    }

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        SQL sql = method.getAnnotation(SQL.class);
        if (sql == null) {
            return proxy.invokeSuper(object, args);
        } else {
            Class<?> declaringClass = method.getDeclaringClass();
            SQLParameter[] sqlParameters = createParameters(method.getParameters(), args);

            if (SQLExecutionType.SELECT_ONE.equals(sql.executionType()))
                return databaseSession.executeQuery(tableClass, declaringClass, sql, sqlParameters);
            else if (SQLExecutionType.SELECT_MANY.equals(sql.executionType()))
                return databaseSession.executeQueryMany(tableClass, declaringClass, sql, sqlParameters);
            else
                return databaseSession.executeUpdate(tableClass, declaringClass, sql, sqlParameters);
        }
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
