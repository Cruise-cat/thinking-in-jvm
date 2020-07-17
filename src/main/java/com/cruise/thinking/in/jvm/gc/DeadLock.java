package com.cruise.thinking.in.jvm.gc;

/**
 * 线程死锁示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class DeadLock {
    static class A {
    }

    static class B {
    }

    static A a = new A();
    static B b = new B();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (a) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    System.out.println("end a");
                }
            }

        }).start();

        new Thread(() -> {
            synchronized (b) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a) {
                    System.out.println("end b");
                }
            }

        }).start();
    }

}
