package org.yx.mongotest.jdk8.interface8;

import org.springframework.stereotype.Component;

/**
 * @author yangxin
 */
@Component
public class Cat implements Obj {
    @Override
    public void say() {
        System.out.println("喵喵喵喵...");
    }
}
