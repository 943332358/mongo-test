package org.yx.mongotest.authorization.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yx.mongotest.authorization.entity.User;
import org.yx.mongotest.authorization.service.MongoService;

import javax.annotation.Resource;

/**
 * @author yangxin
 */
@RestController
@RequestMapping("mongo")
public class MongoController {
    @Resource
    private MongoService mongoService;

    @RequestMapping("insert")
    public User insert(User users) {
        return mongoService.insert(users);
    }

    @PostMapping("/test1")
    public void tradePay(@RequestBody PayOrderTO payOrderTo) throws Exception {
        System.out.println(payOrderTo);
    }


}
