package com.antsix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

//如果该类不在 root package下，此时需要添加扫描配置
//@ComponentScan(basePackages={"com.antsix"})
@SpringBootApplication

//开启缓存功能
@EnableCaching
public class Chapter1Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter1Application.class, args);
	}

}
/**
 * Spring Boot中通过@EnableCaching注解自动化配置合适的缓存管理器（CacheManager），Spring Boot根据下面的顺序去侦测缓存提供者
 * (对应的类 org.springframework.boot.autoconfigure.cache---CacheType.enum)
 * Generic
 * JCache
 * EhCache
 * Hazelcast
 * Infinispan
 * Redis
 * Guava
 * Simple
 *  以上在 org.springframework.boot.autoconfigure.cache包下，都可以找到对应的类。
 *
 * 除了按顺序侦测外，我们也可以通过配置属性spring.cache.type来强制指定。
 * 缓存的对象必须实现Serializable
 * 除GuavaCacheManager之外都支持Spring事务，即回滚时Cache的数据也会被移除
 */