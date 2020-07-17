package com.cruise.thinking.in.jvm.optimize;


/**
 * 标量替换示例
 * <p>
 * -Xms100M -Xmx100M -XX:+DoEscapeAnalysis -XX:+PrintGCDetails -XX:-EliminateAllocations，439ms 且触发垃圾回收
 * -Xms100M -Xmx100M -XX:+DoEscapeAnalysis -XX:+PrintGCDetails -XX:+EliminateAllocations,5ms
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class ScalarReplace {

    private static void alloc() {
        User user = new User();
        user.a = 2;
        user.b = "哈哈";
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
        public int a;
        public String b;
    }
}
