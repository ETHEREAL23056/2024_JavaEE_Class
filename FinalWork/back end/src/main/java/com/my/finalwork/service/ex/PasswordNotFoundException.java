package com.my.finalwork.service.ex;

// 密码错误异常
public class PasswordNotFoundException extends ServiceException {
    public PasswordNotFoundException() {
    }

    public PasswordNotFoundException(String message) {
        super(message);
    }

    public PasswordNotFoundException(Throwable cause) {
        super(cause);
    }

    public PasswordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause);
    }
}
