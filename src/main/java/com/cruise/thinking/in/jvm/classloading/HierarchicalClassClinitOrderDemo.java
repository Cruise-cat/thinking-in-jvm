package com.cruise.thinking.in.jvm.classloading;

/**
 * 层级的<clinit>的执行顺序示例
 * 通过 jclasslib Bytecode Viewer 插件观察
 * javap -p -v HierarchicalClassClinitOrderDemo$Parent.class
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/14
 */
public class HierarchicalClassClinitOrderDemo {
    public static void main(String[] args) {
        // B -> 3
        System.out.println(Sub.B);
    }

    public static class Parent {
        // 第一个执行
        static {
            A = 1;
        }

        // 第二个执行
        public static int A = 2;

        // 第三个执行
        static {
            A = 3;
        }
    }

    public static class Sub extends Parent {
        // 第四个执行
        public static int B = A;
    }
}
