package com.haigeek.shiro.service.impl;

import com.haigeek.shiro.dao.repository.RoleRepository;
import com.haigeek.shiro.dao.repository.UserRepository;
import com.haigeek.shiro.dao.repository.UserRoleRepository;
import com.haigeek.shiro.model.dto.SysRole;
import com.haigeek.shiro.model.dto.UserInfo;
import com.haigeek.shiro.model.entity.Permission;
import com.haigeek.shiro.model.entity.Role;
import com.haigeek.shiro.model.entity.User;
import com.haigeek.shiro.model.entity.UserRole;
import com.haigeek.shiro.service.RoleService;
import com.haigeek.shiro.service.UserService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author zhaohj
 * @date 2019/4/30 下午2:09
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    Mapper mapper;

    @Override
    public List<Role> getUserRole(Integer userId) {
        List<Role> roles=roleRepository.findAll();
        List<Role> result=new ArrayList<>();
        List<UserRole> userRoles=userRoleRepository.findByUserId(userId);
        for (Role o:roles) {
            for (UserRole userRole:userRoles) {
                if(o.getId().equals(userRole.getRoleId())){
                    result.add(o);
                }
            }
        }
        return result;
    }
    @Override
    public UserInfo getUserInfo(Integer userId) {
        //获取用户的角色信息
        List<Role> roles=this.getUserRole(userId);
        Optional<User> user=userRepository.findById(userId);
        //获取角色的权限信息
        List<SysRole>sysRoles=new ArrayList<>();
        for (Role role:roles) {
            List<Permission> permissions=roleService.getRolePermission(role.getId());
            SysRole sysRole=mapper.map(role,SysRole.class);
            sysRole.setPermissions(permissions);
            sysRoles.add(sysRole);
        }
        UserInfo userInfo=mapper.map(user.get(),UserInfo.class);
        userInfo.setRoles(sysRoles);
        return userInfo;
    }

    @Override
    public User getUserInfo(String userName) {
        return userRepository.findByUserName(userName);
    }
}
