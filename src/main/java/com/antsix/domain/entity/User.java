package com.antsix.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column()
    private String name;

    @Column()
    private Integer age;

    public User(){}
    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
    // 省略setter和getter


}
