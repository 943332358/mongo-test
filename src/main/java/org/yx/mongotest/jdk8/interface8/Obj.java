package org.yx.mongotest.jdk8.interface8;

/**
 * @author yangxin
 */
public interface Obj {

    /**
     * 实现类可以不覆盖该方法
     */
    default void say() {
        System.out.println("Obj say............");
    }

    /**
     * 静态方法实现类无法覆盖
     */
    static void run() {
        System.out.println("Obj run............");
    }


}
