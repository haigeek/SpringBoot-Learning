package com.haigeek.exception;

/**
 * 解密异常
 * @author haigeek
 * @date 2018/12/10
 */
public class DecryptException extends Exception{

    private static String DECRYPT_EXCEPTION = "密文无法解密";

    public DecryptException() {
        super(DECRYPT_EXCEPTION);
    }

    public DecryptException(String msg) {
        super(msg);
    }
}
