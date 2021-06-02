package org.yx.mongotest.oauth2Client.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangxin
 */
@RequestMapping("test/oauth")
@RestController
public class ClientTestController {

    @RequestMapping("redirect")
    public void redirect(String code) {

    }
}
