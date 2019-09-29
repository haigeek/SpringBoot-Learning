package com.haigeek.shiro.service;

import com.haigeek.shiro.model.dto.UserInfo;
import com.haigeek.shiro.model.entity.Role;
import com.haigeek.shiro.model.entity.User;

import java.util.List;

/**
 * @author zhaohj
 * @date 2019/4/30 上午10:51
 */
public interface UserService {

    /**
     *获取用户的角色
     */
    List<Role>getUserRole(Integer userId);

    /**
     * 获取用户的角色权限信息
     * @param userId
     * @return
     */
    UserInfo getUserInfo(Integer userId);


    User getUserInfo(String userName);




}
