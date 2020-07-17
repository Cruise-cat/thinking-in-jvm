package com.cruise.thinking.in.jvm.engine;

/**
 * 方法调用——动态分派
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
public class DynamicDispatch {

    static abstract class Human {
        protected abstract void sayHello();
    }

    static class Man extends Human {
        @Override
        protected void sayHello() {
            System.out.println("man say hello");
        }
    }

    static class Woman extends Human {
        @Override
        protected void sayHello() {
            System.out.println("woman say hello");
        }
    }

    /**
     * 这里选择调用的方法版本是不可能再根据静态类型来决定的，因为静态类型同样都是Human
     * 的两个变量man和woman在调用sayHello()方法时产生了不同的行为。导致这个现象的原因很明显，
     * 是因为这两个变量的实际类型不同
     * 使用 invokevirtual 指令调用方法
     *
     * @param args
     */
    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();//man say hello
        woman.sayHello();//woman say hello
        man = new Woman();
        man.sayHello();//woman say hello
    }
}
