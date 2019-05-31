package org.braisdom.drucker.database;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.braisdom.drucker.annotation.SQL;
import org.braisdom.drucker.annotation.SQLParam;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class TableBehaviorProxy implements MethodInterceptor {

    private final DatabaseSession databaseSession;
    private final Class<? extends TableBehavior> tableClass;
    private final Class<? extends TableRow> tableRowClass;

    public TableBehaviorProxy(DatabaseSession databaseSession,
                              Class<? extends TableBehavior> tableClass,
                              Class<? extends TableRow> tableRowClass) {
        this.databaseSession = databaseSession;
        this.tableClass = tableClass;
        this.tableRowClass = tableRowClass;
    }

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        SQL sql = method.getAnnotation(SQL.class);
        if (sql == null)
            return proxy.invokeSuper(object, args);

        SQLParameter[] sqlParameters = createParameters(method.getParameters(), args);

        if (SQLExecutionType.SELECT_ONE.equals(sql.executionType()))
            return databaseSession.executeQuery(tableClass, tableRowClass, sql, sqlParameters);
        else if (SQLExecutionType.SELECT_MANY.equals(sql.executionType()))
            return databaseSession.executeQueryMany(tableClass, tableRowClass, sql, sqlParameters);
        else
            return databaseSession.executeUpdate(tableClass, tableRowClass, sql, sqlParameters);
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
