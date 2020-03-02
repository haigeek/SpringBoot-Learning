package com.haigeek.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.servlet.KaptchaServlet;
import com.haigeek.shiro.exception.MyExceptionResolver;
import com.haigeek.shiro.shiro.KaptchaFilter;
import com.haigeek.shiro.shiro.MyShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhaohj
 * @date 2019/4/28 下午10:14
 */
@Configuration
public class ShiroConfig {

    //配置kaptcha图片验证码框架提供的Servlet
    @Bean
    public ServletRegistrationBean kaptchaServlet() {
        ServletRegistrationBean servlet = new ServletRegistrationBean(new KaptchaServlet(), "/kaptcha.jpg");
        servlet.addInitParameter(Constants.KAPTCHA_SESSION_CONFIG_KEY, Constants.KAPTCHA_SESSION_KEY);//session key
        servlet.addInitParameter(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "50");//字体大小
        servlet.addInitParameter(Constants.KAPTCHA_BORDER, "no");
        servlet.addInitParameter(Constants.KAPTCHA_BORDER_COLOR, "105,179,90");
        servlet.addInitParameter(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "45");
        servlet.addInitParameter(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
        servlet.addInitParameter(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, "宋体,楷体,微软雅黑");
        servlet.addInitParameter(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "blue");
        servlet.addInitParameter(Constants.KAPTCHA_IMAGE_WIDTH, "125");
        servlet.addInitParameter(Constants.KAPTCHA_IMAGE_HEIGHT, "60");
        //可以设置很多属性,具体看com.google.code.kaptcha.Constants
        return servlet;
    }

    //注入异常处理类
    @Bean
    public MyExceptionResolver myExceptionResolver() {
        return new MyExceptionResolver();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        //设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //验证码过滤器
        Map<String, Filter> filterMap=shiroFilterFactoryBean.getFilters();
        KaptchaFilter kaptchaFilter = new KaptchaFilter();
        filterMap.put("kaptchaFilter", kaptchaFilter);
        shiroFilterFactoryBean.setFilters(filterMap);
        // 拦截器
        //rest：比如/admins/user/**=rest[user],根据请求的方法，相当于/admins/user/**=perms[user：method] ,其中method为post，get，delete等。
        //port：比如/admins/user/**=port[8081],当请求的url的端口不是8081是跳转到schemal：//serverName：8081?queryString,其中schmal是协议http或https等，serverName是你访问的host,8081是url配置里port的端口，queryString是你访问的url里的？后面的参数。
        //perms：比如/admins/user/**=perms[user：add：*],perms参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，比如/admins/user/**=perms["user：add：*,user：modify：*"]，当有多个参数时必须每个参数都通过才通过，想当于isPermitedAll()方法。
        //roles：比如/admins/user/**=roles[sba],参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，当有多个参数时，比如/admins/user/**=roles["sba,guest"],每个参数通过才算通过，相当于hasAllRoles()方法。//要实现or的效果看http://zgzty.blog.163.com/blog/static/83831226201302983358670/
        //anon：比如/admins/**=anon 没有参数，表示可以匿名使用。
        //authc：比如/admins/user/**=authc表示需要认证才能使用，没有参数
        //authcBasic：比如/admins/user/**=authcBasic没有参数表示httpBasic认证
        //ssl：比如/admins/user/**=ssl没有参数，表示安全的url请求，协议为https
        //user：比如/admins/user/**=user没有参数表示必须存在用户，当登入操作时不做检查
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        //配置记住我或认证通过可以访问的地址
        filterChainDefinitionMap.put("/index", "user");
        filterChainDefinitionMap.put("/", "user");
        filterChainDefinitionMap.put("/login", "kaptchaFilter");
        // <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //这段是配合 actuator框架使用的，配置相应的角色才能访问
        // filterChainDefinitionMap.put("/health", "roles[aix]");//服务器健康状况页面
        // filterChainDefinitionMap.put("/info", "roles[aix]");//服务器信息页面
        // filterChainDefinitionMap.put("/env", "roles[aix]");//应用程序的环境变量
        // filterChainDefinitionMap.put("/metrics", "roles[aix]");
        // filterChainDefinitionMap.put("/configprops", "roles[aix]");
        //开放的静态资源
        filterChainDefinitionMap.put("/favicon.ico", "anon");//网站图标
        filterChainDefinitionMap.put("/static/**", "anon");//配置static文件下资源能被访问的，这是个例子
        filterChainDefinitionMap.put("/kaptcha.jpg", "anon");//图片验证码(kaptcha框架)

        filterChainDefinitionMap.put("/api/v1/**", "anon");//API接口

        // swagger接口文档
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/doc.html", "anon");

        // 其他的
        filterChainDefinitionMap.put("/**", "authc");

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 未授权界面，不生效(详情原因看MyExceptionResolver)
        shiroFilterFactoryBean.setUnauthorizedUrl("/errorView/403_error.html");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(myShiroRealm());
        //注入缓存管理器
        securityManager.setCacheManager(ehCacheManager());//这个如果执行多次，也是同样的一个对象;
        //注入记住我管理器;
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
     */
    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    /**
     * 凭证匹配器 （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * 所以我们需要修改下doGetAuthenticationInfo中的代码; @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);// 散列的次数，比如散列两次，相当于md5(md5(""));
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);//表示是否存储散列后的密码为16进制，需要和生成密码时的一样，默认是base64；
        return hashedCredentialsMatcher;
    }

    /**
     * 开启shiro aop注解支持. 使用代理方式; 所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * shiro缓存管理器;
     * 需要注入对应的其它的实体类中：
     * 1、安全管理器：securityManager
     * 可见securityManager是整个shiro的核心；
     *
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return cacheManager;
    }

    /**
     * cookie管理对象;
     *
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        //System.out.println("ShiroConfiguration.rememberMeManager()");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }

    /**
     * cookie对象;
     *
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        //System.out.println("ShiroConfiguration.rememberMeCookie()");
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    @Bean(name = "sessionValidationScheduler")
    public ExecutorServiceSessionValidationScheduler getExecutorServiceSessionValidationScheduler() {
        ExecutorServiceSessionValidationScheduler scheduler = new ExecutorServiceSessionValidationScheduler();
        scheduler.setInterval(900000);
        return scheduler;
    }

    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }






}
