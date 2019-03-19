package com.antsix.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class User {

    private Long id;
    private String name;
    private Integer age;

    // 省略setter和getter

}
