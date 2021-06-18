package org.yx.mongotest.common.function;

/**
 * @author yangxin
 */
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t) throws Exception;
}
