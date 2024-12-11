package com.my.finalwork.service.ex;

// 已付款分期数大于需要付款分期数异常
public class OwnerPaymentInstallmentOverflowException extends ServiceException {
    public OwnerPaymentInstallmentOverflowException() {
    }

    public OwnerPaymentInstallmentOverflowException(String message) {
        super(message);
    }

    public OwnerPaymentInstallmentOverflowException(String message, Throwable cause) {
        super(message, cause);
    }

    public OwnerPaymentInstallmentOverflowException(Throwable cause) {
        super(cause);
    }

    public OwnerPaymentInstallmentOverflowException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
