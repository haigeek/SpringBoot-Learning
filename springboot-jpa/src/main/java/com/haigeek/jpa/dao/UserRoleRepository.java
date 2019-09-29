package com.haigeek.jpa.dao;

import com.haigeek.jpa.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author zhaohj
 * @date 2019/4/30 上午10:48
 */
public interface UserRoleRepository extends JpaRepository<UserRole,Integer> {
    @Query(value = "select r.* from user_role ur inner join role r on ur.role_id=r.id where ur.user_id = ?1",nativeQuery = true)
    List<Object[]> getRoleByUserId(Integer userId);
}
