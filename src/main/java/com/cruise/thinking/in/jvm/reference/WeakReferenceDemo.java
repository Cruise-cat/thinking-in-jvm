package com.cruise.thinking.in.jvm.reference;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 弱引用示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class WeakReferenceDemo {

    public static void main(String[] args) {
        //demo();
        soft();
    }

    public static void demo() {
        // 写法一
        WeakReference<C> weakReference = new WeakReference<>(new C(1, "Cruise"));
        // 写法二
        //C c = new C(1, "Cruise");
        //WeakReference<C> weakReference = new WeakReference<>(c);
        // 必须设置 b = null 否则强引用依然存在
        //c = null;
        System.out.println(weakReference.get());
        // 释放
        weakReference.clear();
        // System.gc();
        // 判断是否被回收
        System.out.println(weakReference.get());
    }

    /**
     * -Xms20m -Xmx20m -XX:+PrintGCDetails -verbose:gc
     */
    public static void soft() {
        List<WeakReference<byte[]>> list = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            WeakReference<byte[]> reference = new WeakReference<>(new byte[1024 * 1024 * 4]);
            System.out.println(reference.get());
            list.add(reference);
        }

        System.out.println("==========");
        for (WeakReference<byte[]> reference : list) {
            System.out.println(reference.get());
        }

    }

    public static class C {
        private int id;
        private String name;

        public C(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
