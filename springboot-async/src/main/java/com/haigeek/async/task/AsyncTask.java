package com.haigeek.async.task;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @author zhaohj
 * @date 2019/4/17 上午9:58
 */
@Component
public class AsyncTask {
    public static final Logger log= LoggerFactory.getLogger(AsyncTask.class);
    @Async
    public void dealNoResultTask(){
        log.info("返回值为void的异步调用开始,使用线程为"+Thread.currentThread().getName());
        try {
            Thread.sleep(3000);;
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        log.info("返回值为空的异步调用结束，使用线程为"+Thread.currentThread().getName());
    }

    @Async
    public Future<String> dealHaveReturnTask(int i){
        log.info("有返回值的异步调用开始，使用线程为"+Thread.currentThread().getName()+"参数为"+ i);
        Future<String>future;
        try {
            Thread.sleep(1000*i);
            future=new AsyncResult<String>("异步调用成功"+i);
        }catch (InterruptedException e){
            future=new AsyncResult<String>("异步调用失败");
        }
        return future;
    }

}
