package com.antsix.domain.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class CustomizedProperties {

    @Value("${com.antsix.name}")
    private String name;
    @Value("${com.antsix.age}")
    private Integer age;
    @Value("${com.antsix.desc}")
    private String desc;
}
