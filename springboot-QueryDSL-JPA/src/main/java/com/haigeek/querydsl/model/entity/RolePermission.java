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
@Entity(name = "ROLE_PERMISSION")
public class RolePermission implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "roleId")
    private Integer roleId;
    @Column(name = "permissionId")
    private Integer permissionId;
}
