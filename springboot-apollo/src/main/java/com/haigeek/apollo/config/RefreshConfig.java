package com.haigeek.apollo.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author zhaohj
 * @date 2019/3/2 下午3:58
 */
public class RefreshConfig {
//    @ApolloConfig
//    //inject config for namespace application
//    private Config config;
//
//    @ApolloConfig("application")
//    private Config anotherConfig;
//
//    @Value("${timeout}")
//    private String timeout;
//
//    //配置配置的监听事件，当远程有更新时拉取更新
//    @ApolloConfigChangeListener
//    private void someOnChange(ConfigChangeEvent changeEvent) {
//        //update injected value of batch if it is changed in Apollo
//        if (changeEvent.isChanged("timeout")) {
//            timeout = config.getProperty("timeout","100");
//        }
//    }

}
