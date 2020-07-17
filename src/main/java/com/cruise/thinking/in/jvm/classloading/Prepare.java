package com.cruise.thinking.in.jvm.classloading;

/**
 * 类加载的准备阶段
 *
 * 通过 javap -p -v Prepare 反编译字节码观察
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/14
 */
public class Prepare {
    /**
     * 静态变量，准备阶段并没有初始化，只是赋初始零值
     */
    private static int num = 10;
    /**
     * 基本类型的静态常量，准备阶段赋值123
     */
    private static final int NUMBER = 123;
    /**
     * 引用类型的静态常量，准备阶段没有对 OBJ 初始化
     */
    public static final Object OBJ = new Object();
    /**
     * 字符串类型的静态常量，编译时赋值 hello
     */
    public static final String STR = "hello";

}
