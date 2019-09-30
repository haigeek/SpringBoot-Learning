package com.haigeek.webservice.model;

import lombok.Data;

/**
 * @author zhaohj
 * @date 2019-09-29 9:49 PM
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;

    public User() {
    }

    public User(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
