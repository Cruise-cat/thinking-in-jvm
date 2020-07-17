package com.cruise.thinking.in.jvm.stringtable;

/**
 * 字符串拼接示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class StringSplicing {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
    }

    public static void test1() {
        // 编译器优化为 abc 存入常量池
        String str = "a" + "b" + "c";
        String str2 = "abc";

        System.out.println(str == str2); // true
    }

    public static void test2() {
        String str = "javaee";
        String str2 = "mysql";
        String str3 = "javaeemysql";
        // 如果在拼接前后出现了变量则相当于 new String() 存放在堆中
        String str4 = "javaee" + "mysql";
        String str5 = "javaee" + str2;
        String str6 = str + "mysql";
        String str7 = str + str2;
        System.out.println(str3 == str4);//true
        System.out.println(str3 == str5);//true
        System.out.println(str3 == str6);//false
        System.out.println(str3 == str7);//false
        System.out.println(str5 == str6);//false
        System.out.println(str5 == str7);//false
        System.out.println(str6 == str7);//false

        String str8 = str6.intern();
        System.out.println(str3 == str8);//true
    }

    public static void test3() {
        String a = "a";
        String b = "b";
        String ab = "ab";
        /**
         * 编译器处理：
         * new StringBuilder
         * append(a)
         * append(b)
         * StringBuilder.toString() 相当于 new String()
         */
        String s = a + b;
        System.out.println(ab == s);// false
    }

    //===============for 循环中的字符串拼接

    public static void test4() {
        final String a = "a";
        final String b = "b";
        String ab = "ab";
        // a 和 b 是常量会进行编译器优化
        String s = a + b;
        System.out.println(ab == s);// true
    }

    public static void test5() {
        long start = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            stringBuilder.append("a");
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void test6() {
        long start = System.currentTimeMillis();
        String str = "";
        // 每循环一次字符串拼接都会创建一个 StringBuilder 对象
        // StringBuilder 每调用一次 toString() 方法会创建一个 String 对象，所以依然会对内存产生过多消耗。
        for (int i = 0; i < 10000; i++) {
            str = str + "a";
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
