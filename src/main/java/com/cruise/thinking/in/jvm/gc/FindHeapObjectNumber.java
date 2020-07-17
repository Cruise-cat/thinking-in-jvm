package com.cruise.thinking.in.jvm.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * 查看占用堆内存中对象的个数
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class FindHeapObjectNumber {
    public static void main(String[] args) throws InterruptedException {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            list.add(new Student());
        }
        Thread.sleep(100000);
        test();
    }

    public static void test() throws InterruptedException {
        System.out.println("------");
        Thread.sleep(100000);
    }

    static class Student {
        private byte[] bytes = new byte[1024 * 1024];
    }
}
