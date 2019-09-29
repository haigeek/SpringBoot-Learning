package com.haigeek.shiro.dao.repository;

import com.haigeek.shiro.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhaohj
 * @date 2019/4/30 上午10:48
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUserName(String userName);

}
