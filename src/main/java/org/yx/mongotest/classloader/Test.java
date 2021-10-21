package org.yx.mongotest.classloader;

import lombok.SneakyThrows;
import lombok.var;

/**
 * @author yangxin
 * @date 2021-10-11 14:47
 * @since v1.6.5
 */
public class Test {
    @SneakyThrows
    public static void main(String[] args) {
        MyClassLoader loader = new MyClassLoader(Test.class.getClassLoader());

        System.out.println("loader name---- " + loader.getParent().getClass().getName());

        var clazz = loader.loadClass("org.yx.mongotest.classloader.Frugalis");

        System.out.println("Loaded class name: " + clazz.getName());

        var instance = clazz.newInstance();

        clazz.getMethod("printMyName").invoke(instance);
    }
}
