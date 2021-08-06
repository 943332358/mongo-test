package org.yx.mongotest.jdk8.interface8;

import org.springframework.stereotype.Component;

/**
 * @author yangxin
 */
@Component
public class Dog implements Obj {
    @Override
    public void say() {
        System.out.println("汪汪汪汪.......");
    }
}
