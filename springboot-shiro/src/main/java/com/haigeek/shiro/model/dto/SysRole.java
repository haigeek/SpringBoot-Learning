package com.haigeek.shiro.model.dto;

import com.haigeek.shiro.model.entity.Permission;
import com.haigeek.shiro.model.entity.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaohj
 * @date 2019/4/30 上午11:03
 */
@Data
public class SysRole extends Role implements Serializable {
    private List<Permission>permissions;
}
