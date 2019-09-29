package com.haigeek.web;

import com.haigeek.model.entity.User;
import com.haigeek.response.ResponseData;
import com.haigeek.response.ResponseUtil;
import com.haigeek.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhaohj
 * @date 2019/1/17 下午10:19
 */
@RestController
public class OauthController {

    @Autowired
    private UserService userService;
    @ApiOperation(value = "获取用户列表",notes = "")
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseData getUserList(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<User> users= (List<User>) userService.getAllUser();
        return ResponseUtil.success(users);
    }
}
