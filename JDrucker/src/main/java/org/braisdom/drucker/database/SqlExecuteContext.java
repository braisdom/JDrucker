package org.braisdom.drucker.database;

import org.braisdom.drucker.annotation.Sql;
import org.braisdom.drucker.annotation.SqlParam;

public interface SqlExecuteContext {

    Sql getSql();

    int getSqlParamSize();

    SqlParam getSqlParam(int index);

    Object getSqlParamValue(int index);

}
