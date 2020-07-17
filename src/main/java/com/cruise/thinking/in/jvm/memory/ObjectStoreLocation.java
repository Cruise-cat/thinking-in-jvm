package com.cruise.thinking.in.jvm.memory;

/**
 * 对象在运行时数据区的存储位置
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
public class ObjectStoreLocation {

    /**
     * 结论： 不管是实例变量、静态变量、局部变量，所有的对象都是在堆中分配内存，而对象的引用会分别存放在不同的位置
     * 如静态变量对应方法区，局部变量对应局部变量表
     */
    public static class A {
        private static B b = new B();
        private B b1 = new B();

        public void test() {
            B b2 = new B();
        }
    }

    public static class B {

    }

    public static void main(String[] args) {
        new A().test();
    }
}

