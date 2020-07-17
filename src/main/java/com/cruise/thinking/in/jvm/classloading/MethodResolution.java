package com.cruise.thinking.in.jvm.classloading;

/**
 * 解析阶段对方法解析时拒绝解析的场景
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/14
 */
public class MethodResolution {
    interface Interface0 {
        void show();
    }

    interface Interface1 {
        void show();
    }

    static class Demo implements Interface0, Interface1 {
        @Override
        public void show() {
            System.out.println("dance");
        }
    }

    public static void main(String[] args) {
        // 不会拒绝编译
        new Demo().show();
    }

}
