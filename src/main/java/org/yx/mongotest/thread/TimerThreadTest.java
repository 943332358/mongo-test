package org.yx.mongotest.thread;

import lombok.SneakyThrows;

import java.time.LocalTime;

/**
 * @author yangxin
 */
public class TimerThreadTest {
    @SneakyThrows
    public static void main(String[] args) {
        Thread t = new TimerThread();
        t.setDaemon(true);
        t.start();
    }

    static class TimerThread extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println(LocalTime.now());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
}
