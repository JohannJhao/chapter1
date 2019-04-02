package com.antsix.chapter1;

import com.antsix.domain.entity.User;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpringBootRedisTest {

    private static final Logger logger = Logger.getLogger(SpringBootTest.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, User> redisTemplate;
    @Test
   public void simpleTets(){

        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set("love","nn");
        logger.debug("debug -- "+valueOperations.get("love"));
        logger.info("info -- "+valueOperations.get("love"));
        logger.warn("warn -- "+valueOperations.get("love"));
        logger.error("error -- "+valueOperations.get("love"));

        // 保存对象
        User user = new User("超人", 20); //User 需要实现 Serializable 接口
        redisTemplate.opsForValue().set(user.getName(), user);

        user = new User("蝙蝠侠", 30);
        redisTemplate.opsForValue().set(user.getName(), user);

        user = new User("蜘蛛侠", 40);
        redisTemplate.opsForValue().set(user.getName(), user);

        logger.warn(redisTemplate.opsForValue().get("超人"));
        logger.warn(redisTemplate.opsForValue().get("蝙蝠侠"));
        logger.warn(redisTemplate.opsForValue().get("蜘蛛侠"));
    }
}
