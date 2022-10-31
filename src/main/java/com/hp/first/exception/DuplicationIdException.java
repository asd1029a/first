package com.hp.first.exception;

public class DuplicationIdException extends RuntimeException {

    public DuplicationIdException() {
        super();
    }

    public DuplicationIdException(String message) {
        super(message);
    }

    public DuplicationIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicationIdException(Throwable cause) {
        super(cause);
    }

    protected DuplicationIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
