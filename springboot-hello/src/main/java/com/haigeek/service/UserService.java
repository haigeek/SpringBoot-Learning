package com.haigeek.service;

import com.haigeek.model.dto.UserDTO;
import com.haigeek.model.dto.UserLoginDTO;
import com.haigeek.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    /**
     * 新建user
     * @param userDTO
     */
    void create(UserDTO userDTO);

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


    /**
     * 用户登录验证
     * @param userLoginDTO
     * @return
     */
    User validate(UserLoginDTO userLoginDTO) throws Exception;
}
