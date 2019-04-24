package com.antsix.domain.mapper;

import com.antsix.domain.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    @Select("select * from USER where name = #{name}")
    User findByName(@Param("name") String name);

    /**
     * Param注解传参
     * @param name
     * @param age
     * @return
     */
    @Insert("insert into USER(name,age) values (#{name},#{age})")
    int insertUser(@Param("name") String name,@Param("age") Integer age);

    /**
     * 通过Map传参。。。
     *
     *  jdbcType参数值，需要为 大写字母
     *
     * @param map
     * @return
     */
    @Insert("insert into user(name,age) values (#{name,jdbcType=VARCHAR},#{age,jdbcType=INTEGER})")
    int insertByMap(Map<String,Object> map);

    /**
     * java对象传参
     * @param user
     * @return
     */
    @Insert("insert into user(name,age) values (#{name},#{age})")
    int insertByUser(User user);

    @Update("UPDATE user SET age=#{age} WHERE name=#{name}")
    void update(User user);

    @Delete("DELETE FROM user WHERE id =#{id}")
    void delete(Long id);

    /**
     * 返回结果，以实体类包装
     * @return
     */
    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age")
    })
    @Select("SELECT name, age FROM user")
    List<User> findAll();
}
