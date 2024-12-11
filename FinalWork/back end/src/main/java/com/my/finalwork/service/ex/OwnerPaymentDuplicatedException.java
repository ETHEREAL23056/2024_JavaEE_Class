package com.my.finalwork.service.ex;

// 支付记录重复异常
public class OwnerPaymentDuplicatedException extends ServiceException {
    public OwnerPaymentDuplicatedException() {
    }

    public OwnerPaymentDuplicatedException(String message) {
        super(message);
    }

    public OwnerPaymentDuplicatedException(Throwable cause) {
        super(cause);
    }

    public OwnerPaymentDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public OwnerPaymentDuplicatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
