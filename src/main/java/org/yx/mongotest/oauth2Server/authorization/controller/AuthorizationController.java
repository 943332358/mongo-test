package org.yx.mongotest.oauth2Server.authorization.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yx.mongotest.oauth2Server.authorization.dto.AuthorizeDto;
import org.yx.mongotest.oauth2Server.authorization.dto.UserAuthorizationDto;
import org.yx.mongotest.oauth2Server.authorization.service.AuthorizationService;

import javax.annotation.Resource;

/**
 * @author yangxin
 */
@Controller
@RequestMapping("oauth")
public class AuthorizationController {
    @Resource
    private AuthorizationService authorizationService;

    /**
     * 校验客户端信息，生成交换token使用的code并返回给客户端
     */
    @RequestMapping("toClient")
    public void toClient(UserAuthorizationDto authorization) {
        authorizationService.redirectClient(authorization);
    }

    @RequestMapping("authorize")
    public String authorize(AuthorizeDto authorize, Model model) {
        model.addAttribute("client", authorizationService.clientCheck(authorize));
        return "authorization";
    }
}
