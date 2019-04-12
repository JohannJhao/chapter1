package com.antsix.chapter1;

import com.antsix.domain.entity.User;
import com.antsix.domain.repository.UserMongoRepository;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpringBootMongoTest {

    //private static final Logger logger = LogManager.getLogger(SpringBootTest.class);
    private static final Logger logger = Logger.getLogger(SpringBootTest.class.getName());

    @Autowired
    private UserMongoRepository userMongoRepository;

    @Before
    public void setUp() {
        userMongoRepository.deleteAll();
        logger.warn("DELETE ALL !!!!!!!!!!!!!!!");
    }

    @Test
    public void test() throws Exception{

        userMongoRepository.save(new User(1L,"Lee",16));
        userMongoRepository.save(new User(2L,"Chao",18));
        userMongoRepository.save(new User(3L,"Kim",20));
        logger.warn("size : "+userMongoRepository.findAll().size());


        logger.warn("User : "+userMongoRepository.findOne(3L));
        userMongoRepository.delete(3L);
        logger.warn("size : "+userMongoRepository.findAll().size());

        User u = userMongoRepository.findByName("Lee");
        logger.warn("User : "+u);
        userMongoRepository.delete(u);
        logger.warn("size : "+userMongoRepository.findAll().size());

    }
}
