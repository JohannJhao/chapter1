package com.antsix.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 创建 RabbitMQ 配置类，用来配置队列、交换器、路由等高级信息
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue helloZyhQueue(){
        return new Queue("helloZyh");
    }
}
