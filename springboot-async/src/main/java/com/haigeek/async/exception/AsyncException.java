package com.haigeek.async.exception;

/**
 * @author zhaohj
 * @date 2019/4/17 上午10:36
 */
public class AsyncException extends Exception {
        private int code;
        private String msg;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
