    package com.hm;

    import com.hm.entity.User;
    import com.hm.service.UserService;
    import org.junit.Test;
    import org.junit.runner.RunWith;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.data.redis.core.RedisTemplate;
    import org.springframework.data.redis.core.StringRedisTemplate;
    import org.springframework.test.context.junit4.SpringRunner;

    import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SBRedisTests {


    //操作的是复杂类型，User
    @Autowired
    RedisTemplate redisTemplate;
    //针对 的都是操作字符串
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    //自定义的json序列化器
    @Autowired
    RedisTemplate jsonRedisTemplate;
    @Autowired
    UserService userService;

    /**
     * 五大数据类型
     * stringRedisTemplate.opsForValue();//操作字符串
     * stringRedisTemplate.opsForList();//操作List
     * stringRedisTemplate.opsForSet();//操作Set
     * stringRedisTemplate.opsForZSet();//操作ZSet
     * stringRedisTemplate.opsForHash();//操作Hash
     */
    @Test
    public void contextLoads() {
 stringRedisTemplate.opsForValue().set("name", "test_redis");
        String name = stringRedisTemplate.opsForValue().get("name");
        System.out.println(name);
// stringRedisTemplate.opsForList().leftPush("myList", "a");
// stringRedisTemplate.opsForList().leftPushAll("myList", "b", "c", "d");
        List<String> myList = stringRedisTemplate.opsForList().range("myList", 0, -1);
        System.out.println(myList);//[d, c, b, a]
    }

    @Test
    public void testRedis() {
//当我们导入了reids的启动器之后 ，springboot会采用redis作为 默认缓存，simple缓存就没有匹配上了
        User user = userService.getUserById(4);
//保存的数据对象必须序列化 implements Serializable
//因为redisTemplate默认采用的是jdk序列化器
// redisTemplate.opsForValue().set("user", user);
        User user1 = (User) redisTemplate.opsForValue().get("user");
        System.out.println(user1);
        jsonRedisTemplate.opsForValue().set("user2", user);
    }
}