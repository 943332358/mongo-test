package org.yx.mongotest.thread;

import lombok.SneakyThrows;

/**
 * @author yangxin
 */
public class Interrupt2 {
    @SneakyThrows
    public static void main(String[] args) {
        HelloThread t = new HelloThread();
        t.start();
        Thread.sleep(1);
        t.setRunning(false);
    }

    static class HelloThread extends Thread {
        public volatile boolean running = true;

        public void setRunning(boolean running) {
            this.running = running;
            System.out.println("t.running：" + running);
        }

        @Override
        public void run() {
            int n = 0;

            while (running) {
                n++;
                System.out.println(n + " hello!");
            }
            System.out.println("end!");
        }
    }
}
