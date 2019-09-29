package com.haigeek.jpa.service.impl;

import com.haigeek.jpa.dao.RolePermissionRepository;
import com.haigeek.jpa.model.entity.Permission;
import com.haigeek.jpa.service.RoleService;
import com.haigeek.jpa.utils.JpaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhaohj
 * @date 2019/4/30 下午2:16
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RolePermissionRepository rolePermissionRepository;


    @Override
    public List<Permission> getRolePermission(List<Integer> roleIdList) {
        List<Permission>result;
        List<Object[]> objects=rolePermissionRepository.findPermissionByRoleId(roleIdList);
        result= JpaUtil.convertObjectArrayToBean(objects,Permission.class);
        return result;
    }
}
