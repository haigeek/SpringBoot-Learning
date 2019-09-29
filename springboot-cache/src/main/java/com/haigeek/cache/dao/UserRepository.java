package com.haigeek.cache.dao;


import com.haigeek.cache.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *author haigeek
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByName(String name);
}
