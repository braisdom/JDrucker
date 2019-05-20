package org.braisdom.drucker.database;

public class EntityInstantiateException extends Exception {
    public EntityInstantiateException() {
    }

    public EntityInstantiateException(String message) {
        super(message);
    }

    public EntityInstantiateException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityInstantiateException(Throwable cause) {
        super(cause);
    }

    public EntityInstantiateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
