package com.haigeek.webservice.service.impl;

import com.haigeek.webservice.model.User;
import com.haigeek.webservice.service.ICommonService;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * @author zhaohj
 * @date 2019-09-29 9:51 PM
 */
@WebService(serviceName = "CommonService", // 与接口中指定的name一致
        targetNamespace = "http://model.webservice.haigeek.com/", // 与接口中的命名空间一致,一般是接口的包名倒
        endpointInterface = "com.haigeek.webservice.service.ICommonService"// 接口地址
)
@Component
public class CommonServiceImpl implements ICommonService {

    @Override
    public String sayHello(String name) {
        return "Hello ," + name;
    }

    @Override
    public User getUser(String name) {
        return new User(1000L, name, 23);
    }
}
