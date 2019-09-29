package com.haigeek.cache.controller;

import com.haigeek.cache.model.entity.User;
import com.haigeek.cache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author zhaohj
 * @date 2019/4/16 下午3:08
 */
@RestController
public class userController {

    @Autowired
    UserService userService;
    @GetMapping(value ="/users" )
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping(value ="/user" )
    public Optional<User> getUser(long id){
        return userService.getUser(id);
    }

    @RequestMapping(value ="/user",method = RequestMethod.POST)
    public Boolean addUser(User user){
        userService.create(user);
        return true;
    }

    @RequestMapping(value ="/user",method = RequestMethod.PUT)
    public User updateUser(User user){
        userService.update(user);
        return user;
    }
}
