package com.my.finalwork.service.ex;

// 用户重复绑定异常
public class UserBoundDuplicatedException extends ServiceException {
    public UserBoundDuplicatedException() {
    }

    public UserBoundDuplicatedException(String message) {
        super(message);
    }

    public UserBoundDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserBoundDuplicatedException(Throwable cause) {
        super(cause);
    }

    protected UserBoundDuplicatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
