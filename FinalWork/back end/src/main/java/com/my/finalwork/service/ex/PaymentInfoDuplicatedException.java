package com.my.finalwork.service.ex;

// 订单重复异常
public class PaymentInfoDuplicatedException extends ServiceException {
    public PaymentInfoDuplicatedException() {
    }

    public PaymentInfoDuplicatedException(String message) {
        super(message);
    }

    public PaymentInfoDuplicatedException(Throwable cause) {
        super(cause);
    }

    public PaymentInfoDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentInfoDuplicatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
