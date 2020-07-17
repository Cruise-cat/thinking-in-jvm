package com.cruise.thinking.in.jvm.classloading;

/**
 * 解析阶段对字段解析时拒绝解析的场景
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/14
 */
public class FieldResolution {
    interface Interface0 {
        int A = 0;
    }

    interface Interface1 extends Interface0 {
        int A = 1;
    }

    interface Interface2 {
        int A = 2;
    }

    static class Parent implements Interface1 {
        public static int A = 3;
    }

    static class Sub extends Parent implements Interface2 {
        // 如果注释这行代码 接口与父类同时存在字段A
        // Javac 编译器将提示对 FieldResolution.java:33: 错误: 对A的引用不明确
        // 并拒绝编译
        public static int A = 4;
    }

    public static void main(String[] args) {
        System.out.println(Sub.A);
    }
}
