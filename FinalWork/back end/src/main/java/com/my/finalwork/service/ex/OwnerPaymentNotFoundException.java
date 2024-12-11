package com.my.finalwork.service.ex;

// 支付记录不存在异常
public class OwnerPaymentNotFoundException extends ServiceException {
    public OwnerPaymentNotFoundException() {
    }

    public OwnerPaymentNotFoundException(String message) {
        super(message);
    }

    public OwnerPaymentNotFoundException(Throwable cause) {
        super(cause);
    }

    public OwnerPaymentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OwnerPaymentNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
