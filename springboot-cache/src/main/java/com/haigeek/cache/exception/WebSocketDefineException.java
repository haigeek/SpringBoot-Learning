package com.haigeek.cache.exception;

/**
 * @author haigeek
 * @date 2018/12/21
 */
public class WebSocketDefineException extends Exception{

    private static String WEBSOCKET_DEFINE_EXCEPTION = "websocket异常";

    public WebSocketDefineException() {
        super(WEBSOCKET_DEFINE_EXCEPTION);
    }

    public WebSocketDefineException(String msg) {
        super(msg);
    }
}
