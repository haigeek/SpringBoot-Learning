package com.haigeek.web;

import com.haigeek.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author haigeek
 */
@Controller
public class HelloController {

    @RequestMapping(value = "sayhello",method = RequestMethod.GET)
    public ModelAndView view(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("content","hello");
        mav.setViewName("time");
        return mav;
    }


}
