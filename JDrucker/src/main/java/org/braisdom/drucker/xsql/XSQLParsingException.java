package org.braisdom.drucker.xsql;

public class XSQLParsingException extends XSQLException {
    public XSQLParsingException() {
    }

    public XSQLParsingException(String message) {
        super(message);
    }

    public XSQLParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public XSQLParsingException(Throwable cause) {
        super(cause);
    }

    public XSQLParsingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
