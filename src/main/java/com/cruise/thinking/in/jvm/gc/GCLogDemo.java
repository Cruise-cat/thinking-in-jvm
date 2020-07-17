package com.cruise.thinking.in.jvm.gc;

import java.util.ArrayList;

/**
 * GC 日志示例
 * -Xms10M -Xmx10M 搭配：
 * <li>-XX:+PrintGC</li>
 * <li>-verbose:gc</li>
 * <li>-XX:+PrintGCDetails</li>
 * <li>-XX:+PrintGCTimeStamps，搭配-XX:+PrintGCDetails</li>
 * <li>-XX:+PrintGCDateStamps，搭配-XX:+PrintGCDetails</li>
 * <li>-XX:+PrintHeapAtGC</li>
 * <li>-Xloggc:../logs/gc.log</li>
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class GCLogDemo {

    public static void main(String[] args) {
        ArrayList<String> objects = new ArrayList<>();
        int i = 0;
        String str = "_index";
        while (true) {
            objects.add(String.valueOf(i).concat(str));
        }
    }
}
