package com.cruise.thinking.in.jvm.stringtable;

/**
 * {@link String#intern()} 练习
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class StringInternExec {

    /**
     * 基于 JDK 8
     *
     * @param args
     */
    public static void main(String[] args) {
        // 这种方式会在字符串常量池池创建一个 "ab"，而且和堆中的 “ab” 地址不同
        //String str = new String("ab");
        // 这种方式不会在字符串常量池池创建一个 "ab"
        String str = new String("a") + new String("b");
        str.intern();
        String str2 = "ab";
        System.out.println(str == str2);
    }
}
