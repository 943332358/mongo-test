package org.yx.mongotest.security;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author yangxin
 */
@RestController
@RequestMapping("user")
public class UserController {
    @RequestMapping("info")
    public String userInfo(Principal principal) {
        return principal.getName();
    }
}
