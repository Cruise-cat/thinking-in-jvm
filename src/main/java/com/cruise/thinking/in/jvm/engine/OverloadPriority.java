package com.cruise.thinking.in.jvm.engine;

import java.io.Serializable;

/**
 * 重载方法匹配优先级
 * 依次注释重载方法，看会分别调用哪个
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
public class OverloadPriority {

    public static void sayHello(Object arg) {
        System.out.println("hello Object");
    }

    public static void sayHello(int arg) {
        System.out.println("hello int");
    }

    public static void sayHello(long arg) {
        System.out.println("hello long");
    }

    public static void sayHello(Character arg) {
        System.out.println("hello Character");
    }

    public static void sayHello(char arg) {
        System.out.println("hello char");
    }

    public static void sayHello(char... arg) {
        System.out.println("hello char ...");
    }

    public static void sayHello(Serializable arg) {
        System.out.println("hello Serializable");
    }

    /**
     * 输出hello char
     * 注释{@link #sayHello(char)}
     * 输出hello int
     * 注释{@link #sayHello(int)}
     * 输出hello long
     * 注释{@link #sayHello(long)}
     * 输出hello Character
     * 注释{@link #sayHello(Character)}
     * 输出hello Serializable （Character 是 Serializable 的接口实现类）
     * 注释{@link #sayHello(Serializable)}
     * 输出hello Object
     * 注释{@link #sayHello(Object)}
     * 输出hello char...
     * 。。。
     * 按照char>int>long>float>double的顺序转型进行匹配，但不会匹配到byte和short类型的重
     * 载， 因为char到byte或short的转型是不安全的。
     *
     * @param args
     */
    public static void main(String[] args) {
        sayHello('a');
    }
}
