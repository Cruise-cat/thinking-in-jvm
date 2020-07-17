package com.cruise.thinking.in.jvm.memory;

/**
 * 新创建对象优先在 Eden 区分配内存
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
public class PriorityEdenAllocation {

    private static final int _1MB = 1024 * 1024;

    /**
     * JDK 8
     * <p>
     * VM参数，使用 Serial + Serial Old ：
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8  -XX:+UseSerialGC
     * VM参数，使用 Parallel Scavenge + Parallel Old：
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * <ul>
     *     <li>-XX:+PrintGCDetails : 发生垃圾收集行为时打印内存回收日志</li>
     *     <li>-Xms20M: 堆初始内存20M</li>
     *     <li>-Xmx20M: 堆最大内存20M</li>
     *     <li>-Xmn10M: 设置新生代内存10M，所以老年代内存也是10M</li>
     *     <li>-XX:SurvivorRatio=8: 设置 Eden 区与 Survivor 区比例为 8:1</li>
     *     <li>-verbose:gc: 打印垃圾收集信息</li>
     * </ul>
     *
     * @param args
     */
    public static void main(String[] args) {
        testAllocation();
    }

    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB]; // 出现一次Minor GC
    }
}

