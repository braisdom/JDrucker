package org.braisdom.drucker.database;

public class IllegalSQLTypeException extends RuntimeException {
    public IllegalSQLTypeException() {
    }

    public IllegalSQLTypeException(String message) {
        super(message);
    }

    public IllegalSQLTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalSQLTypeException(Throwable cause) {
        super(cause);
    }

    public IllegalSQLTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
