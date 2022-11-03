package com.hp.first.exception;

public class CancelCantException extends RuntimeException{
    public CancelCantException() {
        super("배달 중엔 취소 불가능 합니다.");
    }

    public CancelCantException(String message) {
        super(message);
    }

    public CancelCantException(String message, Throwable cause) {
        super(message, cause);
    }

    public CancelCantException(Throwable cause) {
        super(cause);
    }

    protected CancelCantException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
