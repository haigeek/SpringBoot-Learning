package com.haigeek.apollo.controller;

import com.haigeek.apollo.model.JavaConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaohj
 * @date 2019/3/2 下午10:46
 */
@RestController
public class TestController {

    @Autowired
    JavaConfigBean javaConfigBean;

    @RequestMapping("/test1")
    public String hello1(){
        System.out.println(javaConfigBean.getTimeout()+"");
        return javaConfigBean.getTimeout()+"";
    }
}
