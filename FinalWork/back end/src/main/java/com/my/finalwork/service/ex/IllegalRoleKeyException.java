package com.my.finalwork.service.ex;

// 用户密钥非法异常
public class IllegalRoleKeyException extends ServiceException {
    public IllegalRoleKeyException() {
    }

    public IllegalRoleKeyException(String message) {
        super(message);
    }

    public IllegalRoleKeyException(Throwable cause) {
        super(cause);
    }

    public IllegalRoleKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalRoleKeyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
