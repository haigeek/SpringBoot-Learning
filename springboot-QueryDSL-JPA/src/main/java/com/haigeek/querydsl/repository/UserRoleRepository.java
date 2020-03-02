package com.haigeek.querydsl.repository;

import com.haigeek.querydsl.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

/**
 * @author zhaohj
 * @date 2019/4/30 上午10:48
 */
public interface UserRoleRepository extends JpaRepository<UserRole,Integer>, QuerydslPredicateExecutor<UserRole> {
}
