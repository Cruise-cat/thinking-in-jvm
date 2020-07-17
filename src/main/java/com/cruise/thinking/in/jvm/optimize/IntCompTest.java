package com.cruise.thinking.in.jvm.optimize;

/**
 * 使用不同模式的编译器执行效率示例
 * -Xint: 2890ms
 * -Xcomp: 160ms
 * -Xmixed: 166ms
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class IntCompTest {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        sum();
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - start));
    }

    /**
     * 对 1亿 以内的偶数求和
     */
    public static void sum() {
        long sum = 0;
        for (long i = 0; i < 100000000; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
    }
}
