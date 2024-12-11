package com.my.finalwork.service.ex;

// 抄表数据重复异常
public class MonthDataDuplicatedException extends ServiceException {
    public MonthDataDuplicatedException() {
    }

    public MonthDataDuplicatedException(String message) {
        super(message);
    }

    public MonthDataDuplicatedException(Throwable cause) {
        super(cause);
    }

    public MonthDataDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public MonthDataDuplicatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
