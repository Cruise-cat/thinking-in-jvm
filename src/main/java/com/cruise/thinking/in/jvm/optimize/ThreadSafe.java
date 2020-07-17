package com.cruise.thinking.in.jvm.optimize;

/**
 * 局部变量是否线程安全示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class ThreadSafe {

    public static void main(String[] args) throws InterruptedException {
        // 非线程安全
        StringBuilder b1 = new StringBuilder();
        b1.append("a");
        b1.append("b");
        new Thread(() -> test2(b1)).start();
        b1.append("c");
        b1.append("d");
        System.out.println(b1);

        // 非线程安全
        StringBuilder b2 = test3();
        new Thread(() -> b2.append("1").append("2")).start();
        new Thread(() -> b2.append("3").append("4").append("5")).start();
        Thread.sleep(200);
        System.out.println(b2);
    }

    /**
     * 线程安全
     *
     * @return
     */
    public static void test1() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("1");
        stringBuilder.append("2");
        System.out.println(stringBuilder.toString());
    }

    /**
     * 线程不安全，stringBuilder逃逸
     *
     * @return
     */
    public static void test2(StringBuilder stringBuilder) {
        stringBuilder.append("1");
        stringBuilder.append("2");
        System.out.println(stringBuilder.toString());
    }

    /**
     * 线程不安全，stringBuilder逃逸
     *
     * @return
     */
    public static StringBuilder test3() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("1");
        stringBuilder.append("2");
        return stringBuilder;
    }
}
