package com.cruise.thinking.in.jvm.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 直接内存 {@link OutOfMemoryError} 示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/16
 */
public class DirectMemoryOutOfMemoryError {

    private static final int _1MB = 1024 * 1024;

    /**
     * VM args: -Xmx20M -XX:MaxDirectMemorySize=10M
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Field declaredField = Unsafe.class.getDeclaredFields()[0];
        declaredField.setAccessible(true);
        Unsafe unsafe = (Unsafe) declaredField.get(null);
        int i = 0;
        while (true) {
            // 申请分配内存 OutOfMemoryError
            unsafe.allocateMemory(_1MB);
            //System.out.println(i++);
        }
    }
}
