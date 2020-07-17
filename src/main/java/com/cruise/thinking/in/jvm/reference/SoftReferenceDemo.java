package com.cruise.thinking.in.jvm.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 软引用示例
 * -Xms10M -Xmx10M -XX:+PrintGCDetails
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class SoftReferenceDemo {

    public static void main(String[] args) {
        //demo();
        soft();

    }

    public static void demo() {

        // 写法一
        SoftReference<B> softReference = new SoftReference<>(new B(1, "Cruise"));
        // 写法二
        //B b = new B(1, "Cruise");
        //SoftReference<B> softReference = new SoftReference<>(b);
        // 必须设置 b = null 否则强引用依然存在
        //b = null;
        System.out.println(softReference.get());
        //softReference.clear();
        try {
            // 模拟内存不足
            Byte[] bytes = new Byte[5 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 判断是否被回收
            System.out.println(softReference.get());
        }
    }

    /**
     * 搭配引用队列使用
     * -Xms20m -Xmx20m -XX:+PrintGCDetails -verbose:gc
     */
    public static void soft() {
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        List<SoftReference<byte[]>> list = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            SoftReference<byte[]> reference = new SoftReference<>(new byte[1024 * 1024 * 4], queue);
            System.out.println(reference.get());
            list.add(reference);
        }
        // 观察注释和不注释结果
//        Reference<?> poll = queue.poll();
//        while (poll != null) {
//            list.remove(poll);
//            poll = queue.poll();
//        }

        System.out.println("==========");
        for (SoftReference<byte[]> reference : list) {
            System.out.println(reference.get());
        }

    }

    public static class B {
        private int id;
        private String name;

        public B(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
