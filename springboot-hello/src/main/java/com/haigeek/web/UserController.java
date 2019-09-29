package com.haigeek.web;

import com.haigeek.model.dto.UserDTO;
import com.haigeek.model.entity.User;
import com.haigeek.response.ResponseData;
import com.haigeek.response.ResponseUtil;
import com.haigeek.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author haigeek
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户列表",notes = "")
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseData getUserList(){
        List<User> users= (List<User>) userService.getAllUser();
        return ResponseUtil.success(users);
    }
    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseData postUser(@RequestBody UserDTO userDTO){
        this.userService.create(userDTO);
        return ResponseUtil.success("用户新建成功");
    }
    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)

    public ResponseData getUser(@PathVariable Long id){
        Optional<User> user=userService.getUser(id);
        return ResponseUtil.success(user);
    }

    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public ResponseData putUser(@RequestBody User user){

        return ResponseUtil.success(userService.update(user));
    }

    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseData deleteUser(@PathVariable Long id){
        userService.delete(id);
        return ResponseUtil.success("用户删除成功");
    }

}
