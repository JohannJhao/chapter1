package com.antsix;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

//如果该类不在 root package下，此时需要添加扫描配置
//@ComponentScan(basePackages={"com.antsix"})
@SpringBootApplication
//定时任务启动注解
@EnableScheduling
//异步调用，为了让 @Async 注解能够生效，还需要在Spring Boot的主程序中配置@EnableAsync
@EnableAsync
public class Chapter1Application {

	//private static Logger logger = LogManager.getLogger(Chapter1Application.class.getName());
	private static Logger logger = Logger.getLogger(Chapter1Application.class.getName());
	public static void main(String[] args) {
		logger.info("### INFO Chapter1Application RUNNING ！！！！！！");
		logger.error("### ERROR Chapter1Application RUNNING ！！！！！！");
		SpringApplication.run(Chapter1Application.class, args);
	}

}
