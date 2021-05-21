package org.yx.mongotest.yx.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yx.mongotest.yx.entity.User;
import org.yx.mongotest.yx.service.MongoService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangxin
 */
@RestController
@RequestMapping("mongo")
public class MongoController {
    @Resource
    private MongoService mongoService;

    @RequestMapping("insert")
    public String insert(User users) {
        mongoService.insert(users);
        return "";
    }


}
