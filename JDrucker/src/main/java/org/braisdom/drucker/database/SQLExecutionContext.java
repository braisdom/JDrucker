package org.braisdom.drucker.database;

import org.braisdom.drucker.annotation.SQL;
import org.braisdom.drucker.annotation.SQLParam;

public interface SQLExecutionContext {

    SQL getSql();

    int getSqlParamSize();

    SQLParam getSqlParam(int index);

    Object getSqlParamValue(int index);

}