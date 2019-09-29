package com.haigeek.exception;

/**
 * 逻辑异常
 * @author haigeek
 * @date 2018/12/10
 */
public class LogicException extends Exception{

    private static String LOGIC_EXCEPTION = "业务逻辑异常";

    public LogicException() {
        super(LOGIC_EXCEPTION);
    }

    public LogicException(String msg) {
        super(msg);
    }
}
