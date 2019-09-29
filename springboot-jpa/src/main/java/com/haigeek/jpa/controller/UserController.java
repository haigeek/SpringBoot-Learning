package com.haigeek.jpa.controller;

import com.haigeek.jpa.model.entity.Role;
import com.haigeek.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhaohj
 * @date 2019/5/6 下午10:07
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/role",method = {RequestMethod.GET})
    public List<Role> getUserRole(@RequestParam("userId") Integer userId){
        List<Role> roles=userService.getUserRole(userId);
        return roles;
    }

    @RequestMapping(value = "/user/role/permission",method = {RequestMethod.GET})
    public UserInfo getUserRolePermission(@RequestParam("userId") Integer userId){
        UserInfo userInfo =userService.getUserInfo(userId);
        return userInfo;
    }
}
