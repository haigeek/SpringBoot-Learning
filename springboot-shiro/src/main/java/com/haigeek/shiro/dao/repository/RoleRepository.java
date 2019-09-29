package com.haigeek.shiro.dao.repository;

import com.haigeek.shiro.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhaohj
 * @date 2019/4/30 上午10:48
 */
public interface RoleRepository extends JpaRepository<Role,Integer> {
}
