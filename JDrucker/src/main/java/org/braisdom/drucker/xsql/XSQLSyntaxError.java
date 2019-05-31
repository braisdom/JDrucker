package org.braisdom.drucker.xsql;

public class XSQLSyntaxError extends RuntimeException {
    public XSQLSyntaxError() {
    }

    public XSQLSyntaxError(String message) {
        super(message);
    }

    public XSQLSyntaxError(String message, Throwable cause) {
        super(message, cause);
    }

    public XSQLSyntaxError(Throwable cause) {
        super(cause);
    }

    public XSQLSyntaxError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
