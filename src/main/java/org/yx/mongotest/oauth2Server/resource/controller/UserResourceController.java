package org.yx.mongotest.oauth2Server.resource.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yx.mongotest.common.Result;
import org.yx.mongotest.common.annotation.Permission;
import org.yx.mongotest.oauth2Server.resource.service.UserResourceService;

import javax.annotation.Resource;

/**
 * 提供用户相关信息
 *
 * @author yangxin
 */
@RestController
@RequestMapping("resource/user")
public class UserResourceController {
    @Resource
    private UserResourceService userResourceService;

    @RequestMapping("userInfo")
    @Permission("userInfo")
    public Result<String> userInfo() {
        userResourceService.getUserInfo();
        return Result.ok();
    }

    @RequestMapping("userImg")
    @Permission(value = {"userImg", "userInfo"})
    public Result<String> userImg() {
        System.out.println("userImg");
        return Result.ok();
    }

    @RequestMapping("userPermissions")
    @Permission(value = {"userImg", "userInfo", "userPermissions"}, type = Permission.Type.ANY)
    public Result<String> userPermissions() {
        userResourceService.getUserInfo();
        return Result.ok();
    }
}
