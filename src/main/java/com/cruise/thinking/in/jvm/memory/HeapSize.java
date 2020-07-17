package com.cruise.thinking.in.jvm.memory;

/**
 * 查看堆内存、系统内存
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class HeapSize {

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();

        long totalMemory = runtime.totalMemory() / 1024 / 1024;
        long maxMemory = runtime.maxMemory() / 1024 / 1024;
        System.out.println("-Xms:" + totalMemory + "M");
        System.out.println("-Xmx:" + maxMemory + "M");

        System.out.println("系统内存:" + (totalMemory * 64.0 / 1024) + "G");
        System.out.println("系统内存:" + (maxMemory * 4.0 / 1024) + "G");
    }
}
