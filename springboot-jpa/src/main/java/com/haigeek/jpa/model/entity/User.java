package com.haigeek.jpa.model.entity;

import lombok.Data;

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

    public User(Integer id,String userName, String name, String password, Integer state, String salt) {
        this.id=id;
        this.userName = userName;
        this.name = name;
        this.password = password;
        this.state = state;
        this.salt = salt;
    }
}