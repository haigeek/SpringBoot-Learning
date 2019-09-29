import com.haigeek.async.AsyncApplication;
import com.haigeek.async.service.AsyncService;
import com.haigeek.async.task.AsyncTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author zhaohj
 * @date 2019/4/17 上午10:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AsyncApplication.class)
public class AsyncTest {
    public static final Logger log= LoggerFactory.getLogger(AsyncTask.class);
    @Autowired
    private AsyncTask asyncTask;
    @Autowired
    private AsyncService asyncService;

    @Test
    public void testAsync() throws InterruptedException, ExecutionException {
        asyncTask.dealNoResultTask();
        Future<String> future=asyncTask.dealHaveReturnTask(3);
        //再次调用
        Future<String> future2=asyncTask.dealHaveReturnTask(2);
        log.info("主线程执行完毕");
        log.info(future.get());
        log.info(future2.get());
    }

    @Test
    public void testServiceAsync() throws InterruptedException, ExecutionException {
        Future<String> future=asyncService.dealHaveReturnTask(3);
        log.info("主线程执行完毕");
        log.info(future.get());
    }

}
