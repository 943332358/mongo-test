package org.yx.mongotest.security;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author yangxin
 */
@RestController
@RequestMapping("admin")
public class AdminController {
    @RequestMapping("info")
    public String adminInfo(Principal principal) {
        return principal.getName();
    }
}
