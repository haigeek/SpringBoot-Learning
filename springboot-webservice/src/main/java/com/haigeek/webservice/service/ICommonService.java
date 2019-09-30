package com.haigeek.webservice.service;

import com.haigeek.webservice.model.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author zhaohj
 * @date 2019-09-29 9:50 PM
 */
@WebService(name = "CommonService", // 暴露服务名称
        targetNamespace = "http://model.webservice.haigeek.com/"// 命名空间,一般是接口的包名倒序
)
public interface ICommonService {
    @WebMethod
    public String sayHello(@WebParam(name = "userName") String name);

    @WebMethod
    public User getUser(@WebParam(name = "userName") String name);
}
