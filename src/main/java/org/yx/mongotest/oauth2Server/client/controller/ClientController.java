package org.yx.mongotest.oauth2Server.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.yx.mongotest.common.Result;
import org.yx.mongotest.oauth2Server.client.entity.Oauth2Client;
import org.yx.mongotest.oauth2Server.client.service.ClientService;

import javax.annotation.Resource;

/**
 * @author yangxin
 */
@RestController
@RequestMapping("client")
public class ClientController {
    @Resource
    private ClientService clientService;

    @RequestMapping("add")
    public Result<Oauth2Client> insert(Oauth2Client oauth2Client, MultipartFile clientLogoFile) {
        return Result.ok(clientService.insert(oauth2Client, clientLogoFile));
    }

}
