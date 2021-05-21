package org.yx.mongotest.security;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yx.mongotest.yx.entity.User;

/**
 * @author yangxin
 */
@RestController
@RequestMapping("admin")
public class AdminController {
    @RequestMapping("info")
    public User adminInfo(){
        return new User();
    }
}
