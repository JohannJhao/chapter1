package com.antsix.domain.pri;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//Spring-data-jpa中，只需要编写类似这样的接口就可实现数据访问。不再像我们以往编写了接口时候还需要自己编写接口实现类
//针对User实体创建对应的Repository接口实现对该实体的数据访问
public interface UserRepository extends JpaRepository<User,Long> {

    User findByName(String name);

    //没有任何类SQL语句就完成了两个条件查询方法。源于Spring-data-jpa的一大特性：通过解析方法名创建查询。
    User findByNameAndAge(String name, Integer age);

    @Query("from User u where u.name = :name")
    User findUser(@Param("name") String name);
}
