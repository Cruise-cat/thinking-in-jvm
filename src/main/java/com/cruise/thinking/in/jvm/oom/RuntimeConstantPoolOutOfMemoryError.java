package com.cruise.thinking.in.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常量池 {@link OutOfMemoryError} 异常示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/16
 */
public class RuntimeConstantPoolOutOfMemoryError {

    /**
     * JDK 6 VM args : -XX:PermSize=6M -XX:MaxPermSize=6M
     * JDK 7 VM args : -XX:PermSize=6M -XX:MaxPermSize=6M 不生效
     * JDK 8 + VM args : -XX:MetaspaceSize=1M -XX:MaxMetaspaceSize=1M 不生效
     * JDK 7 + VM args : -Xms6M -Xmx6M 生效
     *
     * @param args
     */
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true)
            list.add(String.valueOf(i++).intern());
    }
}
