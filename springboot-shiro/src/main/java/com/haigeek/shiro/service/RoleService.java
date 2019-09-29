package com.haigeek.shiro.service;

import com.haigeek.shiro.model.entity.Permission;

import java.util.List;

/**
 * @author zhaohj
 * @date 2019/4/30 下午2:03
 */
public interface RoleService {


    /**
     * 获取角色的权限信息
     * @param roleId
     * @return
     */
    List<Permission>getRolePermission(Integer roleId);
}
