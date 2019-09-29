package com.haigeek.jpa.dao;

import com.haigeek.jpa.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author zhaohj
 * @date 2019/4/30 上午10:48
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUserName(String userName);
}
