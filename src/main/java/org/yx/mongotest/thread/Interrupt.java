package org.yx.mongotest.thread;

import lombok.SneakyThrows;
import lombok.var;
import pl.touk.throwing.ThrowingRunnable;

/**
 * @author yangxin
 */
public class Interrupt {

    public static void main(String[] args) throws InterruptedException {
        var t = new Thread(ThrowingRunnable.unchecked(() -> {
            Thread thread = new MyThread();
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("interrupted!");
            }
            thread.interrupt();

        }));
        t.setName("zhangsan");
        t.start();
        Thread.sleep(1000);
        t.interrupt();
        t.join();
        System.out.println("end");
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            int n = 0;
            while (!isInterrupted()) {
                n++;
                System.out.println(n + " hello!");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

}
