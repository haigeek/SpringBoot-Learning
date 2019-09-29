package com.haigeek.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author zhaohj
 * @date 2019/1/12 上午12:17
 */
@Controller
public class ModelAndViewController {

    /**
     * 使用thymeleaf模版
     */
    @RequestMapping(value = "/thymeleaf",method = RequestMethod.GET)
    public String index(Map<String,String> map){
        map.put("name","www.haigeek.cn");
        return "thymeleaf";
    }
    /**
     * 使用freemarker模版
     */
    @RequestMapping(value = "/freemarker",method = RequestMethod.GET)
    public String freemarker(Map<String,String> map){
        map.put("name","www.haigeek.cn");
        return "freemarker";
    }
}
