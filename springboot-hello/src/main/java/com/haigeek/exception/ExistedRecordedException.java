package com.haigeek.exception;

/**
 * 记录已存在
 * @author haigeek
 * @date 2018/12/10
 */
public class ExistedRecordedException extends Exception {

    private static String EXISTED_RECORDED_EXCEPTION = "当前记录已存在";

    public ExistedRecordedException() {
        super(EXISTED_RECORDED_EXCEPTION);
    }

    public ExistedRecordedException(String msg) {
        super(msg);
    }

}
