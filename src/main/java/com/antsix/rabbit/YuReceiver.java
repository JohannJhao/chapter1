package com.antsix.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 创建消息消费者
 */
@Component
public class YuReceiver {

    @RabbitHandler
    @RabbitListener(queues = "helloZyh")
    public void process(String helloZyh) {
        System.out.println("YuReceiver : " + helloZyh);
    }
}
