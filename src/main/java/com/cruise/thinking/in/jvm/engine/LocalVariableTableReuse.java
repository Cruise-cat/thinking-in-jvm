package com.cruise.thinking.in.jvm.engine;

/**
 * 局部变量表的复用对 GC 的影响
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
public class LocalVariableTableReuse {

    /**
     * -verbose:gc
     *
     * @param args
     */
    public static void main(String[] args) {
        LocalVariableTableReuse reuse = new LocalVariableTableReuse();
        //reuse.test1();
        //reuse.test2();
        reuse.test3();
    }

    //================局部变量槽复用对垃圾收集的影响=====================
    // -verbose:gc 查看垃圾收集的过程

    /**
     * 没有回收 arr
     * <p>
     * [GC (System.gc())  16138K->11056K(376832K), 0.0047830 secs]
     * [Full GC (System.gc())  11056K->10903K(376832K), 0.0050762 secs]
     */
    public void test1() {
        byte[] arr = new byte[10 * 1024 * 1024];
        System.gc();
    }

    /**
     * 没有回收 arr
     * <p>但是此方法被即时编译器编译后可以对 arr 正常垃圾回收</p>
     * <p>
     * [GC (System.gc())  16138K->11120K(376832K), 0.0042495 secs]
     * [Full GC (System.gc())  11120K->10902K(376832K), 0.0047444 secs]
     */
    public void test2() {
        {
            byte[] arr = new byte[10 * 1024 * 1024];
        }
        System.gc();
    }

    /**
     * arr 被回收
     * <p>不建议这么使用，建议使用{@link #test2()}的方式，使用合理的变量作用域来控制变量的垃圾回收</p>
     * [GC (System.gc())  16138K->848K(376832K), 0.0007272 secs]
     * [Full GC (System.gc())  848K->662K(376832K), 0.0042875 secs]
     */
    public void test3() {
        {
            byte[] arr = new byte[10 * 1024 * 1024];
        }
        int a = 0;
        System.gc();
    }
}
