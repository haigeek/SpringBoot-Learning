package com.haigeek.shiro.dao.repository;

import com.haigeek.shiro.model.entity.Permission;
import com.haigeek.shiro.model.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author zhaohj
 * @date 2019/4/30 上午10:48
 */
public interface RolePermissionRepository extends JpaRepository<RolePermission,Integer> {
    List<RolePermission> findByRoleId(Integer roleId);
}
