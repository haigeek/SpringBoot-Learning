package com.haigeek.querydsl.model.dto;


import com.haigeek.querydsl.model.entity.Permission;
import com.haigeek.querydsl.model.entity.Role;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaohj
 * @date 2019/4/30 上午11:03
 */
public class SysRole extends Role implements Serializable {
    private List<Permission>permissions;
}
