package org.yx.mongotest.bean;

import lombok.var;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author yangxin
 * @date 2021-10-19 15:30
 * @since v1.6.5
 */
public class BeanLife {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        var testBean = context.getBean(TestBean.class);
        testBean.say("zhangsan");
    }
}
