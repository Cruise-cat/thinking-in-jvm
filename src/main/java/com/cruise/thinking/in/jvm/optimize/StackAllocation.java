package com.cruise.thinking.in.jvm.optimize;


/**
 * 栈上分配示例
 * -Xms1G -Xmx1G -XX:+DoEscapeAnalysis -XX:+PrintGCDetails, 3ms
 * -Xms1G -Xmx1G -XX:-DoEscapeAnalysis -XX:+PrintGCDetails ， 344ms且触发垃圾回收
 * 分别打开和关闭逃逸分析，查看打印的时间
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class StackAllocation {

    private static void alloc() {
        User user = new User();
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 100000000; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - start));
        // 为方便查看对象的个数 睡眠
        Thread.sleep(10000000);
    }


    static class User {

    }
}
