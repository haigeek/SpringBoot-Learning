package com.haigeek.cache.exception;

/**
 * 记录不存在
 * @author haigeek
 * @date 2018/12/10
 */
public class UnRecordedException extends Exception {

    private static String UN_RECORDED_EXCEPTION = "当前记录不存在";

    public UnRecordedException() {
        super(UN_RECORDED_EXCEPTION);
    }

    public UnRecordedException(String msg) {
        super(msg);
    }
}
