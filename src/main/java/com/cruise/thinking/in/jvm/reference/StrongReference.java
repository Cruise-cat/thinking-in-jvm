package com.cruise.thinking.in.jvm.reference;

/**
 * 强引用示例
 * -Xms10M -Xmx10M -XX:+PrintGCDetails
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class StrongReference {

    public static void main(String[] args) {
        A a = new A();
        A b = a;
        a = null;
        System.gc();
        try {
            // 模拟内存不足
            Byte[] bytes = new Byte[5 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(b);
        }


    }

    private static class A {

    }
}
