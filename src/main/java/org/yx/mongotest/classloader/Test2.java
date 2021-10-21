package org.yx.mongotest.classloader;

/**
 * @author yangxin
 * @date 2021-10-14 09:04
 * @since v1.6.5
 */
public class Test2 extends Test1 {
    @Override
    public void aa() {
        System.out.println("test1");
    }

    public static void main(String[] args) {
        Test1 test1 = new Test1();
        test1.aa();
    }
}
