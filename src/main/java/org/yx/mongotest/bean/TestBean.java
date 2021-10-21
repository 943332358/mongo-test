package org.yx.mongotest.bean;

import org.springframework.stereotype.Component;

/**
 * @author yangxin
 * @date 2021-10-19 14:54
 * @since v1.6.5
 */
@Component
public class TestBean {
    public void say(String name) {
        System.out.println("name is " + name);
    }
}
