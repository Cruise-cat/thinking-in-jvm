package com.cruise.thinking.in.jvm.optimize;

/**
 * 同步消除
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class SynchronizeEliminate {

    public void test1() {
        Object obj = new Object();
        synchronized (obj) {
            System.out.println("hello");
        }
    }

    // 同步消除优化后
    public void test2() {
        Object obj = new Object();
        System.out.println("hello");
    }
}
