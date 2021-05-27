package org.yx.mongotest.oauth2Server.authorization.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yx.mongotest.oauth2Server.client.entity.Oauth2Client;

/**
 * @author yangxin
 */
@RestController
@RequestMapping("auth")
public class AuthorizationController {

    /**
     * 校验客户端信息，生成交换token使用的code并返回给客户端
     */
    @RequestMapping("toClient")
    public void toClient(Oauth2Client client) {


    }
}
