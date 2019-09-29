package com.haigeek.shiro.service.impl;

import com.haigeek.shiro.dao.repository.PermissionRepository;
import com.haigeek.shiro.dao.repository.RolePermissionRepository;
import com.haigeek.shiro.model.entity.Permission;
import com.haigeek.shiro.model.entity.RolePermission;
import com.haigeek.shiro.service.RoleService;
import org.hibernate.validator.constraints.LuhnCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

/**
 * @author zhaohj
 * @date 2019/4/30 下午2:16
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RolePermissionRepository rolePermissionRepository;

    @Autowired
    PermissionRepository permissionRepository;


    @Override
    public List<Permission> getRolePermission(Integer roleId) {
        List<Permission>result=new ArrayList<>();
        List<Permission> permissions=permissionRepository.findAll();
        List<RolePermission>rolePermissions=rolePermissionRepository.findByRoleId(roleId);
        for (Permission p:permissions) {
            for (RolePermission rp:rolePermissions
                 ) {
                if(p.getId().equals(rp.getPermissionId())){
                    result.add(p);
                }
            }
        }
        return result;
    }
}
