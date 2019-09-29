package com.haigeek.cache.exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 定义统一的异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    //@ExceptionHandler用来定义函数针对的异常类型
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", e.getMessage());
        mav.addObject("exception", e.fillInStackTrace());
        mav.addObject("path", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }
}