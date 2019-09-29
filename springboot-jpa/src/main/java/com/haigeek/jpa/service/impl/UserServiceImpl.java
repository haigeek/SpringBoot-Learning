package com.haigeek.jpa.service.impl;

import com.haigeek.jpa.dao.UserRepository;
import com.haigeek.jpa.dao.UserRoleRepository;
import com.haigeek.jpa.model.entity.Role;
import com.haigeek.jpa.model.entity.User;
import com.haigeek.jpa.service.RoleService;
import com.haigeek.jpa.service.UserService;
import com.haigeek.jpa.utils.JpaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    @Override
    public List<Role> getUserRole(Integer userId) {
        List<Object[]> objects=userRoleRepository.getRoleByUserId(userId);
        List<Role> roles= JpaUtil.convertObjectArrayToBean(objects,Role.class);
        return roles;
    }

    @Override
    public User getUserInfo(String userName) {
        return userRepository.findByUserName(userName);
    }
}
