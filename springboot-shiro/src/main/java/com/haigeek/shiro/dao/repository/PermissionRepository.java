package com.haigeek.shiro.dao.repository;

import com.haigeek.shiro.model.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhaohj
 * @date 2019/4/30 上午10:48
 */
public interface PermissionRepository extends JpaRepository<Permission,Integer> {

}
