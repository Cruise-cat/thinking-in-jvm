package com.cruise.thinking.in.jvm.classloading;

/**
 * 多线程对一个类进行初始化时导致死锁演示示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/14
 */
public class DeadLockClinitDemo {

    static class DeadLoopClass {
        static {
            // 如果不加上这个if语句， 编译器将提示“Initializer does not complete normally” 并拒绝编译
            if (true) {
                System.out.println(Thread.currentThread() + "init DeadLoopClass");
                // 如果注释这行代码将不会死锁而且只有一个线程可以初始化该类
//                while (true) {
//                }
            }
        }
    }

    public static void main(String[] args) {
        Runnable script = new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread() + "start");
                DeadLoopClass dlc = new DeadLoopClass();
                System.out.println(Thread.currentThread() + " run over");
            }
        };
        // 两个线程将发生死锁都无法继续执行
        Thread thread1 = new Thread(script);
        Thread thread2 = new Thread(script);
        thread1.start();
        // 如果注释这行代码将不会发生死锁
        thread2.start();
    }
}
