package com.haigeek.jpa.model.dto;

import com.haigeek.jpa.model.entity.Permission;
import com.haigeek.jpa.model.entity.Role;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaohj
 * @date 2019/4/30 上午11:03
 */
public class SysRole extends Role implements Serializable {
    private List<Permission>permissions;


    public SysRole(Integer id, String role, String description) {
        super(id, role, description);
    }
}
