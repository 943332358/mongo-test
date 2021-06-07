package org.yx.mongotest.oauth2Server.authorization.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yx.mongotest.common.Result;
import org.yx.mongotest.oauth2Server.authorization.dto.AccessDto;
import org.yx.mongotest.oauth2Server.authorization.dto.AccessTokenDto;
import org.yx.mongotest.oauth2Server.authorization.dto.AuthorizeDto;
import org.yx.mongotest.oauth2Server.authorization.dto.UserAuthorizationDto;
import org.yx.mongotest.oauth2Server.authorization.service.AuthorizationService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
    public void toClient(UserAuthorizationDto authorization, HttpSession session) throws JsonProcessingException {
        authorization.username = session.getAttribute("username").toString();
        authorizationService.redirectClient(authorization);
    }

    @RequestMapping("authorize")
    public String authorize(AuthorizeDto authorize, Model model) {
        model.addAttribute("client", authorizationService.clientCheck(authorize));
        return "authorization";
    }

    @RequestMapping("accessToken")
    @ResponseBody
    public Result<AccessDto> accessToken(AccessTokenDto accessTokenDto) throws Exception {
        return Result.ok(authorizationService.accessToken(accessTokenDto));
    }

    @RequestMapping("refreshToken")
    @ResponseBody
    public Result<AccessDto> refreshToken(String refreshToken) {
        return Result.ok(authorizationService.refreshToken(refreshToken));
    }

}
