package com.haigeek.jpa.dao;

import com.haigeek.jpa.model.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author zhaohj
 * @date 2019/4/30 上午10:48
 */
public interface RolePermissionRepository extends JpaRepository<RolePermission,Integer> {
    List<RolePermission> findByRoleId(Integer roleId);

    /**
     * 根据角色获取权限列表（已取合集）
     * @param roleId
     * @return
     */
    @Query(value = "select distinct p.id,p.permission, p.description from role_permission rp inner join permission p on rp.permission_id=p.id where rp.role_id in ?1 ",nativeQuery = true)
    List<Object[]> findPermissionByRoleId(List<Integer> roleId);
}
