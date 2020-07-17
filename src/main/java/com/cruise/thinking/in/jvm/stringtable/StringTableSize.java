package com.cruise.thinking.in.jvm.stringtable;

/**
 * 通过 jinfo -flag StringTableSize pid 观察不同版本的 JDK 的 字符串常量池 Hashtable 的长度
 * 可以手动设置： -XX:StringTableSize=10
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class StringTableSize {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("starting.....");
        Thread.sleep(100000);
    }
}
