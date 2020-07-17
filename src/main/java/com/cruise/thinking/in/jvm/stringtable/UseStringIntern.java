package com.cruise.thinking.in.jvm.stringtable;


/**
 * {@link String#intern()} 示例
 * <p>如何保证字面量 s 指向字符串常量池</p>
 * <ul>
 *     <li>String s = ""</li>
 *     <li>String s = new String("").intern()</li>
 *     <li>new StringBuilder().toString().intern()</li>
 * </ul>
 *
 * @author Cruise
 * @version 1.0
 * @see String#intern()
 * @since 2020/7/17
 */
public class UseStringIntern {

    public static void main(String[] args) {
        String s = new String("1");
        // 调用此方法前在字符串常量池已经存在 "1"
        s.intern();
        String s1 = "1";

        System.out.println(s == s1);// jdk6 false jdk7 false

        String s3 = new String("1").intern();

        System.out.println(s3 == s1);//jdk7 true

        String s4 = new String("1") + new String("1");
        // 调用此方法前在字符串常量池不存在 "11"
        // 在字符串常量池中生成对象“11”
        // 在 jdk6 : 会在字符串常量池生成一个新的对象“11”
        // 在 jdk7 以后 : 由于此时在堆中已存在“11”，所以并没有真的生成对象，而是一个指向堆中“11”的引用，所以字符串常量池中的“11”和str变量指定相同的地址
        s4.intern();
        // 使用上一步在字符串串常量池中生成的 “11” 的地址
        String s5 = "11";
        System.out.println(s4 == s5);//jdk6 false jdk7 true
    }
}
