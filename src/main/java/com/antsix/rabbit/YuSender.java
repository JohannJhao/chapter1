package com.antsix.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 创建消息生产者
 */
@Component
public class YuSender {

//    @Autowired
//    private AmqpTemplate amqpTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public  void send (){
        String context = "hello" + new Date();
        System.out.println("YuSender : "+context);

        //this.amqpTemplate.convertAndSend("helloZyh",context);
        this.rabbitTemplate.convertAndSend("helloZyh",context);// queueName = "helloZyh"
    }
}
