package org.braisdom.drucker.xsql;

public interface XSQLFunction {

    Object exec(Object... args) throws XSQLFunctionException;
}
