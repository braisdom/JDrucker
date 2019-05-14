package org.braisdom.drucker.xsql;

public class XSQLFunctionException extends RuntimeException {
    public XSQLFunctionException() {
    }

    public XSQLFunctionException(String message) {
        super(message);
    }

    public XSQLFunctionException(String message, Throwable cause) {
        super(message, cause);
    }

    public XSQLFunctionException(Throwable cause) {
        super(cause);
    }

    public XSQLFunctionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
