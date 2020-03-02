package com.haigeek.querydsl.repository;

import com.haigeek.querydsl.model.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

/**
 * @author zhaohj
 * @date 2019/4/30 上午10:48
 */
public interface RolePermissionRepository extends JpaRepository<RolePermission,Integer>, QuerydslPredicateExecutor<RolePermission> {

}
