package com.haigeek.jpa.service;


import com.haigeek.jpa.model.entity.Role;
import com.haigeek.jpa.model.entity.User;

import java.util.List;


/**
 * @author zhaohj
 * @date 2019/4/30 上午10:51
 */
public interface UserService {

    /**
     *获取用户的角色
     */
    List<Role> getUserRole(Integer userId);


    /**
     * 获取用户的基本信息
     * @param userName
     * @return
     */
    User getUserInfo(String userName);

}
