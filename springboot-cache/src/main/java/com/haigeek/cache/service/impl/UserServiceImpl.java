package com.haigeek.cache.service.impl;

import com.haigeek.cache.dao.UserRepository;
import com.haigeek.cache.exception.IllegalParameterException;
import com.haigeek.cache.exception.UnRecordedException;
import com.haigeek.cache.model.entity.User;
import com.haigeek.cache.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author haigeek
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;
    /**
     * 新建用户,将新的返回值替换缓存中的值，新建用户之后，将所有用户信息的缓存清空
     * @param user
     */
    @Override
    @Caching(
            put = {@CachePut(value = "userCache",key = "#user.id")},
            evict = {@CacheEvict(value = "allUsersCache",allEntries = true)}
    )
    public User create(User user) {
        logger.info("新建用户start"+user.getId());
        return userRepository.save(user);
    }

    /**
     * 修改用户,将新的返回值替换缓存中的值，新建用户之后，将所有用户信息的缓存清空
     * @param user
     * @return
     */
    @Override
    @Caching(
            put = {@CachePut(value = "userCache",key = "#user.id")},
            evict = {@CacheEvict(value = "allUsersCache",allEntries = true)}
    )
    public User update(User user) {
        logger.info("修改用户start"+user.getName());
        return userRepository.save(user);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "userCache", key = "#id"),
                    @CacheEvict(value = "allUsersCache", allEntries = true)
            }
    )
    public void delete(Long id) {
        logger.info("删除用户start...");
        userRepository.deleteById(id);

    }

    /**
     * Cacheable表示在此处使用缓存，使用参数的id表示缓存的key，#result为空的时候，不缓存
     * @param id
     * @return
     */
    @Override
    @Cacheable(value = "userCache",key = "#id",unless = "#result==null")
    public Optional<User> getUser(Long id) {
        logger.info("获取用户start...");
        return userRepository.findById(id);
    }

    @Override
    @Cacheable(value = "allUsersCache",unless = "#result.size()==0")
    public List<User> getAllUser() {
        logger.info("获取所有的用户列表");
        return userRepository.findAll();
    }

}
