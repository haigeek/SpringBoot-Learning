package com.haigeek.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author zhaohj
 * @date 2018/12/13
 * 返回一个dozer实例bean，目的是做对象间属性值的赋值操作
 */
@Configuration
public class DozerConfig {
    @Bean
    public DozerBeanMapper mapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList("config/dozerBeanMapping.xml"));
        return mapper;
    }
}
