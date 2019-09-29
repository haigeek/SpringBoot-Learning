package com.haigeek.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author zhaohj
 * @date 2019/1/9 下午11:47
 */
@Controller
public class CookieController {

    @RequestMapping(value = "/test/cookie",method = {RequestMethod.GET})
    public String cookie(@RequestParam("browser") String browser, HttpServletRequest request, HttpSession session) {
        //false代表服务器没有session的话不新建
        HttpSession session1=request.getSession(false);
        if(session1==null){
            System.out.println("服务器不存在session");
        }
        HttpSession session2=request.getSession(true);
        if (session2 == null) {
            System.out.println("服务器没有新建session");
        } else {
            System.out.println("存在session，browser=" + session2.toString());
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + " : " + cookie.getValue());
            }
        }
        return "index";
    }
    @RequestMapping(value = "/test/v2/cookie",method = {RequestMethod.GET})
    public String cookie2(@RequestParam("browser") String browser, HttpServletRequest request, HttpSession session) {
        //取出session中的browser
        Object sessionBrowser = session.getAttribute("browser");
        if (sessionBrowser == null) {
            System.out.println("不存在session，设置browser=" + browser);
            session.setAttribute("browser", browser);
        } else {
            System.out.println("存在session，browser=" + sessionBrowser.toString());
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + " : " + cookie.getValue());
            }
        }
        return "index";
    }
}
