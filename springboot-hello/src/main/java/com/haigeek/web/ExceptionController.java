package com.haigeek.web;

import com.haigeek.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zhaohj
 * @date 2019/1/12 上午12:17
 */

@Controller
public class ExceptionController {

    /**
     * 使用自定义异常
     */
    @RequestMapping(value = "/json",method = RequestMethod.GET)
    public String json() throws MyException {
        throw new MyException("发生错误");
    }
}
