package com.antsix.chapter1;

/**
 * 自定义属性测试
 */

import com.antsix.config.CustomizedProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomizedPropertiesTest {
    @Autowired
    private CustomizedProperties customizedProperties;

    @Value("${com.antsix.number1}")
    private int number1;

    /**
     * 在命令行，通过命令行设置属性值。对application.properties中的server.port进行赋值。
     * 等价于，在application.properties中添加属性server.port=8888
     * java -jar xxx.jar --server.port=8888
     *
     * 屏蔽命令行访问属性的设置
     * SpringApplication.setAddCommandLineProperties(false)
     */

    /**
     * application.properties中配置通用内容，并设置spring.profiles.active=dev，以开发环境为默认配置
     * application-{profile}.properties中配置各个环境不同的内容
     * 通过命令行方式去激活不同环境的配置
     * java -jar xxx.jar 使用的是 application.properties 中指定的默认配置，即dev
     * java -jar xxx.jar --spring.profiles.active=test 使用的是测试环境配置(test)
     *
     * 举个栗子：
     * 在dev，test，prod 配置文件中，配置不同的 server.port 属性值。设定 {spring.profiles.active=dev}，此时使用的是dev配置文件中的端口值。
     *
     * 注：
     *      如果 application.properties 文件中也设定了 server.port 属性值。
     *      此时其他的 application-{profile}.properties 配置文件中的 server.port 属性值不会生效
     */

    @Test
    public void test() throws Exception {
        System.out.println(customizedProperties.getName());
        System.out.println(customizedProperties.getAge());
        System.out.println(customizedProperties.getDesc());
        System.out.println("number1 : "+number1);
    }
}
