package com.cruise.thinking.in.jvm.gc;

/**
 * 验证 Java 没有使用引用计数法判断对象是否需要被垃圾回收
 * 观察方法执行完后 Eden 区所占用的内存是 0%，说明循环引用的对象被回收了
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class ExcludeReferenceCounter {

    private ExcludeReferenceCounter obj;

    public void setObj(ExcludeReferenceCounter obj) {
        this.obj = obj;
    }

    public static void main(String[] args) throws InterruptedException {
        ExcludeReferenceCounter obj1 = new ExcludeReferenceCounter();
        ExcludeReferenceCounter obj2 = new ExcludeReferenceCounter();
        obj1.setObj(obj2);
        obj2.setObj(obj1);

        obj1 = null;
        obj2 = null;

        Thread.sleep(1000);
        //
        System.gc();
    }
}
