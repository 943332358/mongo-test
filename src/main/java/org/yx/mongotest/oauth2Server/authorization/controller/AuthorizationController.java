package org.yx.mongotest.oauth2Server.authorization.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.yx.mongotest.oauth2Server.authorization.dto.AuthorizeDto;
import org.yx.mongotest.oauth2Server.authorization.service.AuthorizationService;
import org.yx.mongotest.oauth2Server.client.entity.Oauth2Client;

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
    public void toClient(Oauth2Client client) {

    }

    @RequestMapping("authorize")
    public ModelAndView authorize(AuthorizeDto authorize, ModelAndView view) {
        view.setViewName("authorization");
        view.addObject(authorizationService.clientCheck(authorize));
        return view;
    }
}
