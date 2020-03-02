package com.haigeek.querydsl.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author zhaohj
 * @date 2019/4/28 下午10:54
 */
@Data
@Entity(name = "USER_ROLE")
public class UserRole implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_id",nullable = false)
    private Integer userId;
    @Column(name = "role_id",nullable = false)
    private Integer roleId;
}
