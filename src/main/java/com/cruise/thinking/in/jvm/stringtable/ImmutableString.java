package com.cruise.thinking.in.jvm.stringtable;

/**
 * 字符串的不可变性
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class ImmutableString {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test5();
    }

    public static void test1() {
        String str = "abc";// 通过字面量定义字符串，放入常量池
        String str2 = "abc"; // 从常量池中取
        System.out.println(str == str2);//true
    }

    public static void test2() {
        String str = "abc";
        String str2 = str.replace("a", "1");
        System.out.println(str);//abc
        System.out.println(str2);//1bc
    }

    public static void test3() {
        String str = new String("good");
        char[] chars = {'t', 'e', 's', 't'};
        test4(str, chars);
        System.out.println(str);//good
        System.out.println(chars);//oest
    }

    public static void test4(String str, char[] chars) {
        str = "hello";
        chars[0] = 'o';
    }

    public static void test5() {
        String str = "abc";
        String str2 = str;
        str += "efd";
        System.out.println(str);//abcefd
        System.out.println(str2);//abc
    }
}
