package org.yx.mongotest.jdk8.lambda;

/**
 * @author yangxin
 */
public class TestLambda {
    public static void main(String[] args) {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名实现");
            }
        };
        r1.run();


        Runnable r2 = () -> System.out.println("lambda 实现");
        r2.run();
    }
}
