package com.haigeek.querydsl.repository;


import com.haigeek.querydsl.model.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author zhaohj
 * @date 2019/4/30 上午10:48
 */
public interface PermissionRepository extends JpaRepository<Permission,Integer>, QuerydslPredicateExecutor<Permission> {
}
