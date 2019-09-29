package com.haigeek.apollo.config;


import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaohj
 * @date 2019/3/2 下午3:21
 */
@Configuration
@EnableApolloConfig
public class AppConfig {
    @Bean
    public ApolloApiConfig apolloApiConfig(){
        return new ApolloApiConfig();
    }
//    @Bean
//    public RefreshConfig refreshConfig(){
//        return new RefreshConfig();
//    }
}

