import com.haigeek.cache.CacheApplication;
import com.haigeek.cache.model.entity.User;
import com.haigeek.cache.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

/**
 * @author zhaohj
 * @date 2019/4/16 上午10:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CacheApplication.class)
@Transactional
public class userCacheTest {
    @Autowired
    UserService userService;
    @Test
    public void testCache(){
        long id = new Random().nextInt(1000);
        //创建一个用户1
        User user = new User();
        user.setId(id);
        user.setAge(123);
        user.setName("test1");
        user.setPwd("pass");
        userService.create(user);
        //创建一个用户2
        long id2 = new Random().nextInt(1000);
        User user2 = new User();
        user2.setId(id2);
        user2.setAge(123);
        user2.setName("test2");
        user2.setPwd("pass");
        userService.create(user2);

        //查询所有用户列表
        userService.getAllUser();
        //再次查询所有用户列表
        userService.getAllUser();
        //再次查询所有用户列表
        userService.getAllUser();
        //再次查询所有用户列表
        userService.getAllUser();
        //查询两次查看缓存命中情况
        Optional<User> user3 = userService.getUser(id); // 第1次访问
        assertEquals(user3.get().getPwd(), "pass");
        Optional<User> user4 = userService.getUser(id); // 第2次访问
        assertEquals(user4.get().getPwd(), "pass");
        //更新用户密码
        user4.get().setPwd("123456");
        userService.update(user4.get());
        //更新后再次查询
        Optional<User> user5 = userService.getUser(id); // 第4次访问
        assertEquals(user5.get().getPwd(), "123456");
        //删除用户
        userService.delete(id);
    }

}
