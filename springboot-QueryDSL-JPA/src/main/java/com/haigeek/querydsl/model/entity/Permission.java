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
 * @date 2019/4/28 下午10:53
 */

@Entity(name = "PERMISSION")
@NoArgsConstructor
@Data
public class Permission implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "permission")
    private String permission;
    @Column(name = "description")
    private String description;

}
