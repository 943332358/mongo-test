package org.yx.mongotest.oauth2Server.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yx.mongotest.common.Result;
import org.yx.mongotest.oauth2Server.client.dto.Oauth2ClientDto;

/**
 * @author yangxin
 */
@RestController
@RequestMapping("client")
public class ClientController {

    public Result<String> insert(Oauth2ClientDto oauth2ClientDto) {
        return Result.ok();
    }

}
