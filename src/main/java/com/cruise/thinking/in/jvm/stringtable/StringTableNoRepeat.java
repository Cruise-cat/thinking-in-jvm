package com.cruise.thinking.in.jvm.stringtable;

/**
 * 字符串常量池中不存在重复的字符串示例
 * Debug 看控制台右上角 Memory 观察 java.lang.String 的个数
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class StringTableNoRepeat {

    public static void main(String[] args) {
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
        System.out.println("4");
        System.out.println("5");
        System.out.println("6");

        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
        System.out.println("4");
        System.out.println("5");

    }
}
