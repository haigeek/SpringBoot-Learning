package com.haigeek.jpa.dao;

import com.haigeek.jpa.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhaohj
 * @date 2019/4/30 上午10:48
 */
public interface RoleRepository extends JpaRepository<Role,Integer> {
}
