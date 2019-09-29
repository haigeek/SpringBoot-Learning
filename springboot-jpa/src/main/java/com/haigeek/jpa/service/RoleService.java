package com.haigeek.jpa.service;

import com.haigeek.jpa.model.entity.Permission;

import java.util.List;

/**
 * @author zhaohj
 * @date 2019/4/30 下午2:03
 */
public interface RoleService {


    /**
     * 获取角色的权限信息
     * @param roleIdList
     * @return
     */
    List<Permission>getRolePermission(List<Integer> roleIdList);

}
