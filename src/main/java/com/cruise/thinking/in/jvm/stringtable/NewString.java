package com.cruise.thinking.in.jvm.stringtable;

/**
 * new String() 到底创建了几个对象
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class NewString {

    public static void main(String[] args) {
        test1();
        test2();
    }

    /**
     * 创建了两个对象，观察 {@link #test1()} 方法的字节码
     * 1.new String("ab") 创建了一个堆对象
     * 2.存放在字符串常量池的对象“ab”，可以观察到 ldc 字节码指令
     * 将第一步堆内对象地址赋给 str
     */
    public static void test1() {
        String str = new String("ab");
    }

    /**
     * 创建了6个对象，观察 {@link #test2()} 方法的字节码 和 {@link StringBuilder#toString()} 方法的字节码
     * 1.因为存在变量的字符串拼接操作，会创建一个 StringBuilder 对象
     * 2.new String("a") 创建了一个堆对象
     * 3.字符串常量池中的对象“a”
     * 4.new String("b") 创建了一个堆对象
     * 5.字符串常量池中的对象“b”
     * 6.{@link StringBuilder#toString()}调用 new String("ab") 创建了一个堆对象，“ab”不会放入常量池中
     * 将第六步堆内对象地址赋给 str
     */
    public static void test2() {
        String str = new String("a") + new String("b");
    }
}
