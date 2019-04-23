package com.antsix.domain.repository;

import com.antsix.domain.entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//增加缓存配置注解
@CacheConfig(cacheNames = "users")
public interface UserRepository extends JpaRepository<User, Long> {

    //表示只有当第一个参数的长度小于 5 的时候才会被缓存，若做此配置上面的AAAAAAA用户就不会被缓存
    @Cacheable(key = "#p0", condition = "#p0.length() < 5")
    User findByName(String name);

    User findByNameAndAge(String name, Integer age);

    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);

}
