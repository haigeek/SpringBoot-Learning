package com.haigeek.apollo.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhaohj
 * @date 2019/3/4 下午11:05
 */
public class ApolloApiConfig {

    private static final Logger logger = LoggerFactory.getLogger(ApolloApiConfig.class);

    private Config config;
    private Config yamlConfig;

    public ApolloApiConfig() {
        ConfigChangeListener changeListener = new ConfigChangeListener() {
            @Override
            public void onChange(ConfigChangeEvent changeEvent) {
                logger.info("Changes for namespace {}", changeEvent.getNamespace());
                for (String key : changeEvent.changedKeys()) {
                    ConfigChange change = changeEvent.getChange(key);
                    logger.info("Change - key: {}, oldValue: {}, newValue: {}, changeType: {}",
                            change.getPropertyName(), change.getOldValue(), change.getNewValue(),
                            change.getChangeType());
                }
            }
        };
        config = ConfigService.getAppConfig();
        config.addChangeListener(changeListener);
        yamlConfig = ConfigService.getConfig("application.yaml");
        yamlConfig.addChangeListener(changeListener);
    }
}
