package com.cruise.thinking.in.jvm.oom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 堆内存报 {@link OutOfMemoryError} 异常
 * 建议配合可视化工具 Java VisualVM 观察 Eden、Survivor、Old 区的对象分配情况
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/16
 */
public class HeapOutOfMemoryError {

    /**
     * VM args : -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>(100000);
        while (true) {
            list.add(new OOMObject());
            //TimeUnit.MILLISECONDS.sleep(10);
        }

    }

    static class OOMObject {

    }
}
