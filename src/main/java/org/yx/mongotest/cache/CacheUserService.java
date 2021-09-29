package org.yx.mongotest.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @author yangxin
 * @date 2021-09-29 10:49
 * @since v1.6.5
 */
@Component
public class CacheUserService {

    @Cacheable("itemCache")
    public String cache() {
        System.out.println("iiniiniiniiniiniiniin");
        return "yangxin";
    }


}
