package com.haigeek.dao;

import com.haigeek.model.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author haigeek
 */
public interface UserRepository extends CrudRepository<User,Long> {
    User findByName(String name);
}
