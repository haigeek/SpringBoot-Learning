package com.haigeek.async.exception;

import com.haigeek.async.task.AsyncTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * @author zhaohj
 * @date 2019/4/17 上午10:33
 * @desc 异步异常处理类
 */
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    public static final Logger log= LoggerFactory.getLogger(AsyncTask.class);

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        log.info("Async method has uncauh=ght exception,params:"+params);

        if (ex instanceof AsyncException) {
            AsyncException asyncException = (AsyncException) ex;
            log.info("asyncException:"  + asyncException.getMsg());
        }

        log.error("Exception :", ex);
    }
}
