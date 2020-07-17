package com.cruise.thinking.in.jvm.gc;

/**
 * CPU 占用过高原因排查
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class HighCpu {

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("a");
            while (true) {

            }
        }).start();

        new Thread(() -> {
            System.out.println("b");
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("b end");
        }).start();

        new Thread(() -> {
            System.out.println("c");
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("c end");
        }).start();
    }
}
