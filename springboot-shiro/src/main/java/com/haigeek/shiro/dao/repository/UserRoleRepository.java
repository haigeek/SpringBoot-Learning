package com.haigeek.shiro.dao.repository;

import com.haigeek.shiro.model.entity.Role;
import com.haigeek.shiro.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhaohj
 * @date 2019/4/30 上午10:48
 */
public interface UserRoleRepository extends JpaRepository<UserRole,Integer> {
    List<UserRole> findByUserId(Integer userId);
}
