package com.my.finalwork.service.ex;

// 抄表数据不存在异常
public class MonthDataNotFoundException extends ServiceException {
    public MonthDataNotFoundException() {
    }

    public MonthDataNotFoundException(String message) {
        super(message);
    }

    public MonthDataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MonthDataNotFoundException(Throwable cause) {
        super(cause);
    }

    public MonthDataNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
