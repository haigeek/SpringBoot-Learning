package com.haigeek.cache.service;
import com.haigeek.cache.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    /**
     * 新建user
     * @param user
     */
    User create(User user);

    /**
     * 更新用户
     * @param user
     * @return
     */
    User update(User user);
    /**
     * 删除用户
     * @param id
     */
    void delete(Long id);

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    Optional<User> getUser(Long id);

    /**
     * 获取所有用户
     */
    List<User> getAllUser();

}
