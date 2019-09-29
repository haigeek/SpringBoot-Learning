package com.haigeek.jpa.controller;

import com.haigeek.jpa.model.entity.Permission;
import com.haigeek.jpa.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaohj
 * @date 2019/5/6 下午5:07
 */
@RestController
public class RoleController {
    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/role",method = {RequestMethod.GET})
    public List<Permission> getRolePermission(@RequestParam("roleId") Integer roleId){
        List<Integer>roleCodes=new ArrayList<>();
        roleCodes.add(roleId);
        List<Permission> permissions=roleService.getRolePermission(roleCodes);
        return permissions;
    }
}
