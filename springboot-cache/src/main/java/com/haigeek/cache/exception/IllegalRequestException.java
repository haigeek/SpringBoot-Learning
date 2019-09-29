package com.haigeek.cache.exception;

/**
 * 请求非法，如未登录
 * @author haigeek
 * @date 2018/12/10
 */
public class IllegalRequestException extends Exception {

    private static final String ILLEGAL_REQUEST_EXCEPTION = "非法请求";

    public IllegalRequestException() {
        super(ILLEGAL_REQUEST_EXCEPTION);
    }

    public IllegalRequestException(String s) {
        super(s);
    }
}
