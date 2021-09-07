package org.yx.mongotest.thread;

import lombok.SneakyThrows;
import pl.touk.throwing.ThrowingRunnable;

/**
 * @author yangxin
 */
public class Join {
    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("aaaaaaa");
        Thread thread = new Thread(ThrowingRunnable.unchecked(() -> {
            Thread.sleep(2);
            System.out.println("bbbbb");
        }));
        thread.start();
        thread.join(3);

        System.out.println("ccccccccccc");
    }
}
