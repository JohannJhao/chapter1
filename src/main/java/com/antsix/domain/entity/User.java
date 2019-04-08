package com.antsix.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

//lombok 可以省略 getter和setter
@Data
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = -1L;
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    public User(){}
    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public User(Long id,String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    // 省略setter和getter

}
