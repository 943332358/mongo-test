package org.yx.mongotest.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yangxin
 * @date 2021-09-29 10:30
 * @since v1.6.5
 */
@RestController
public class CacheUser {
    @Resource
    private CacheUserService cacheUserService;

    @RequestMapping("cacheUser")
    public String test() {
        return cacheUserService.cache();
    }


}
