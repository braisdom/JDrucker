package org.braisdom.drucker.xsql;

public class XSqlException extends RuntimeException {
    public XSqlException() {
    }

    public XSqlException(String message) {
        super(message);
    }

    public XSqlException(String message, Throwable cause) {
        super(message, cause);
    }

    public XSqlException(Throwable cause) {
        super(cause);
    }

    public XSqlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
