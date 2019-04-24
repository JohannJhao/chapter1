package com.antsix.domain.mapper;

import com.antsix.domain.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from USER where name = #{name}")
    User findByName(@Param("name") String name);

    @Insert("insert into USER(name,age) values (#{name},#{age})")
    int insertUser(@Param("name") String name,@Param("age") Integer age);
}
