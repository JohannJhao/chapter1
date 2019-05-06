package com.antsix.chapter1;

import com.antsix.rabbit.YuSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RabbitmqTest {

    @Autowired
    private YuSender sender;

    @Test
    public void send(){
        sender.send();
    }
}
