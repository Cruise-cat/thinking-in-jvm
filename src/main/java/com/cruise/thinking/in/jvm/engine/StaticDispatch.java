package com.cruise.thinking.in.jvm.engine;

import java.util.Random;

/**
 * 方法调用——静态分派
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
public class StaticDispatch {

    static abstract class Human {
    }

    static class Man extends Human {
    }

    static class Woman extends Human {
    }

    public void sayHello(Human guy) {
        System.out.println("hello,guy!");
    }

    public void sayHello(Man guy) {
        System.out.println("hello,gentleman!");
    }

    public void sayHello(Woman guy) {
        System.out.println("hello,lady!");
    }


    /**
     * 代码中的“Human”称为变量的“静态类型”（Static Type）或者叫“外观类型”（Apparent Type）,
     * 后面的“Man”则被称为变量的“实际类型”（Actual Type）或者叫“运行时类型”（Runtime Type）。
     * 静态类型和实际类型在程序中都可能会发生变化，区别是静态类型的变化仅仅在使用时发生，变量本身的静态类型不会被改变，
     * 并且最终的静态类型是在编译期可知的；而实际类型变化的结果在运行期才可确定，编译器在编译程序的时候并不知道一个对象的实际类型是什么。
     *
     * @param args
     */
    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(man);
        sr.sayHello(woman);
        // ==============
        // 实际类型变化
        Human human = (new Random()).nextBoolean() ? new Man() : new Woman();
        // 静态类型变化，证明静态类型是可变的，但这个改变仍是在编译期是可知的
        sr.sayHello((Man) human);
        sr.sayHello((Woman) human);
    }
}
