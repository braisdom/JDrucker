package org.braisdom.drucker.database;

public class BeanReflectionException extends Exception {
    public BeanReflectionException() {
    }

    public BeanReflectionException(String message) {
        super(message);
    }

    public BeanReflectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanReflectionException(Throwable cause) {
        super(cause);
    }

    public BeanReflectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
