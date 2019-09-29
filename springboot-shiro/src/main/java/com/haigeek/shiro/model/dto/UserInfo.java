package com.haigeek.shiro.model.dto;

import com.haigeek.shiro.model.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @author zhaohj
 * @date 2019/4/30 上午10:56
 */
@Data
public class UserInfo extends User {
    private List<SysRole> roles;

    /**
     * 密码盐
     */
    public String getCredentialsSalt() {
        return getUserName() + getSalt();
    }

    @Override
    public String toString() {
        return "username:" + getUserName() + "|name=" + getName();
    }
}
