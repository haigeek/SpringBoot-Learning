package com.haigeek.shiro.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author zhaohj
 * @date 2019/4/28 下午10:53
 */
@Data
@Entity(name = "PERMISSION")
public class Permission implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "permission",nullable = false)
    private String permission;
    @Column(name = "desc",nullable = false)
    private String desc;
}
