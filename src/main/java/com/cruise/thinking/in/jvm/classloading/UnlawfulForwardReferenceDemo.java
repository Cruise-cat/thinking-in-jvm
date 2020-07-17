package com.cruise.thinking.in.jvm.classloading;

/**
 * 非法前向引用变量示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/14
 */
public class UnlawfulForwardReferenceDemo {

    public static int A = 0;

    static {
        A = 1;
        // 可以对B赋值
        B = 1;
        // 编译报错，不能对B访问
        // System.out.println(B);
    }

    public static int B = 0;
}


