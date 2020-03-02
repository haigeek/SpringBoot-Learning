package com.haigeek.querydsl.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author zhaohj
 * @date 2019/4/28 下午10:43
 */
@Data
@Entity(name = "USER")
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "username",nullable = false)
    private String userName;
    @Column(name = "name")
    private String name;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "state",nullable = false)
    private Integer state;
    @Column(name="salt",nullable = false)
    private String salt;

}