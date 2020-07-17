package com.cruise.thinking.in.jvm.memory;

/**
 * 大对象直接在老年代分配
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
public class LargeObjectAllocationOldArea {

    private static final int _1MB = 1024 * 1024;

    /**
     * VM args :
     * 方式一： -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
     * 方式二： -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728 -XX:+UseSerialGC
     * 方式三（默认的Parallel，不支持）： -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
     *
     * @param args
     */
    public static void main(String[] args) {
        byte[] allocation = new byte[4 * _1MB]; //直接分配在老年代中
    }
}
