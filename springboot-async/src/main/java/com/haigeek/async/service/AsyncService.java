package com.haigeek.async.service;

import com.haigeek.async.task.AsyncTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author zhaohj
 * @date 2019/4/17 上午10:42
 * 在service中，一个异步方法调用另外一个加@Async注解的方法是无效的，需要写成一个单独的异步bean并注入生效
 */
@Service
public class AsyncService {
    public static final Logger log= LoggerFactory.getLogger(AsyncTask.class);
    @Async
    public void dealNoResultTask(){
        log.info("[Service]返回值为void的异步调用开始,使用线程为"+Thread.currentThread().getName());
        try {
            Thread.sleep(3000);;
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        log.info("[Service]返回值为空的异步调用结束，使用线程为"+Thread.currentThread().getName());
    }

    @Async
    public Future<String> dealHaveReturnTask(int i){
        log.info("[Service]有返回值的异步调用开始，使用线程为"+Thread.currentThread().getName()+"参数为"+ i);
        Future<String>future;
        try {
            Thread.sleep(1000*i);
            future=new AsyncResult<String>("[Service]异步调用成功"+i);
            //在service中调用本service的方法
            this.dealNoResultTask();
        }catch (InterruptedException e){
            future=new AsyncResult<String>("[Service]异步调用失败");
        }
        return future;
    }
}
