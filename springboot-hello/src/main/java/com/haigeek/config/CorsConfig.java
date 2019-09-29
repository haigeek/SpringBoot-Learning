
package com.haigeek.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @date
 * @author zhaohj
 * desc：跨域的相关配置
 */
@Configuration

@ConditionalOnExpression("${cors.allow}")
public class CorsConfig extends WebMvcConfigurerAdapter {

    @Value("${cors.mapping}")
    private String mapping = "/**";

    @Value("${cors.origin}")
    private String origin = "*";

    @Value("${cors.method}")
    private String method = "*";

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        CrosDomainAllowInterceptor corsInterceptor=new CrosDomainAllowInterceptor();
        registry.addInterceptor(corsInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(this.mapping)
                .allowedOrigins(this.origin)
                .allowedMethods(this.method)
                .allowedHeaders(this.origin)
                .allowCredentials(true);
    }

    class CrosDomainAllowInterceptor implements HandlerInterceptor {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                                 Object handler) throws Exception {
            if(request.getMethod().equals(RequestMethod.OPTIONS.name())) {
                response.setStatus(HttpStatus.OK.value());
            }
            return true;
        }
        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response,
                               Object handler, ModelAndView modelAndView) throws Exception {
        }
        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                    Object handler, Exception ex) throws Exception {
        }
    }
}
