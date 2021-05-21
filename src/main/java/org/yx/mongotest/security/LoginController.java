package org.yx.mongotest.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author yangxin
 */
@RestController
public class LoginController {

    @GetMapping("login")
    public ModelAndView login() {
        return new ModelAndView("login.html");
    }

    @RequestMapping("perform_login")
    public void performLogin() {
        System.out.println("perform_login");
    }
}
