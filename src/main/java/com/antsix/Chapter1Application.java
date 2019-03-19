package com.antsix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//如果该类不在 root package下，此时需要添加扫描配置
//@ComponentScan(basePackages={"com.antsix"})
@SpringBootApplication
public class Chapter1Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter1Application.class, args);
	}

}
