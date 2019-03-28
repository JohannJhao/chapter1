package com.antsix.chapter1;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpringBootRedisTest {

    private static final Logger logger = Logger.getLogger(SpringBootTest.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
   public void simpleTets(){

        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set("love","nn");
        logger.debug("debug -- "+valueOperations.get("love"));
        logger.info("info -- "+valueOperations.get("love"));
        logger.warn("warn -- "+valueOperations.get("love"));
        logger.error("error -- "+valueOperations.get("love"));
    }


}
