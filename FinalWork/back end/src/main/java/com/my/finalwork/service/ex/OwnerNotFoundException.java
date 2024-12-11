package com.my.finalwork.service.ex;

// 业主不存在异常
public class OwnerNotFoundException extends ServiceException {
    public OwnerNotFoundException() {
    }

    public OwnerNotFoundException(String message) {
        super(message);
    }

    public OwnerNotFoundException(Throwable cause) {
        super(cause);
    }

    public OwnerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OwnerNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
