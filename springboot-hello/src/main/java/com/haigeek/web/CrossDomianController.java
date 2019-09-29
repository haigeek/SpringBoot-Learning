package com.haigeek.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhaohj
 * @date 2019/1/12 上午12:22
 */

@Controller
public class CrossDomianController {
    @RequestMapping(value = "corssdomain",method = RequestMethod.GET)
    public ModelAndView view(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("content","nginx处理跨域请求成功");
        mav.setViewName("info");
        return mav;
    }
}
