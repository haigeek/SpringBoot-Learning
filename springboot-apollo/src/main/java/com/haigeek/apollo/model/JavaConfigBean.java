package com.haigeek.apollo.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaohj
 * @date 2019/3/3 下午11:36
 */
@Configuration
public class JavaConfigBean {

    @Value("${timeout:20}")
    private int timeout;

    public int getTimeout() {
        return timeout;
    }
}
