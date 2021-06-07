package org.yx.mongotest.common.annotation;

import java.lang.annotation.*;

/**
 * @author yangxin
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {
    String[] value() default {};

    /**
     * 需要校验的权限范围
     */
    Type type() default Type.ALL;

    enum Type {
        // 包含所有权限
        ALL,
        // 包含单个权限
        ANY
    }
}
