package com.my.finalwork.service.ex;

// 业主信息重复异常
public class OwnerInfoDuplicatedException extends ServiceException{
    public OwnerInfoDuplicatedException() {
    }

    public OwnerInfoDuplicatedException(String message) {
        super(message);
    }

    public OwnerInfoDuplicatedException(Throwable cause) {
        super(cause);
    }

    public OwnerInfoDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public OwnerInfoDuplicatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
