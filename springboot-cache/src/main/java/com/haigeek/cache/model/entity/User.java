package com.haigeek.cache.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "User2")
public class User implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "pwd",nullable = false)
    private String pwd;
    @Column(name = "age",nullable = false)
    private Integer age;

}
