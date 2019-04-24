package com.antsix.chapter1;

import com.antsix.domain.entity.User;
import com.antsix.domain.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//@Transactional
public class SimpleSpringBootMybatisTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    //@Rollback
    public void simpleMybatisTest(){

        userMapper.insertUser("AAA",21);
        User u = userMapper.findByName("AAA");
        System.out.println(u.getAge());
    }
}
