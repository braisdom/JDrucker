package org.braisdom.drucker.xsql;

public class XSQLException extends RuntimeException {
    public XSQLException() {
    }

    public XSQLException(String message) {
        super(message);
    }

    public XSQLException(String message, Throwable cause) {
        super(message, cause);
    }

    public XSQLException(Throwable cause) {
        super(cause);
    }

    public XSQLException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
