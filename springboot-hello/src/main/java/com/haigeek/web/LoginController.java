package com.haigeek.web;

import com.haigeek.model.dto.UserLoginDTO;
import com.haigeek.model.entity.User;
import com.haigeek.response.ResponseData;
import com.haigeek.response.ResponseUtil;
import com.haigeek.service.UserService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaohj
 * @date 2019/1/11 下午11:17
 */

@RestController
@RequestMapping(value = "api/user")
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    private ResponseData login(@RequestBody @ApiParam(value = "传入值") UserLoginDTO userLoginDTO) throws Exception {
        User user=userService.validate(userLoginDTO);
        return ResponseUtil.success(user);
    }

}
