package com.cruise.thinking.in.jvm.optimize;

/**
 * 方法逃逸
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class MethodEscape {

    private Object obj;

    /**
     * 发生逃逸
     *
     * @return
     */
    public Object getInstance() {
        return this.obj == null ? new Object() : obj;
    }

    /**
     * 发生逃逸
     *
     * @return
     */
    public void setObj() {
        this.obj = new Object();
    }

    /**
     * 没有发生逃逸
     *
     * @return
     */
    public void test1() {
        Object o = new Object();
    }

    /**
     * 发生逃逸
     *
     * @return
     */
    public void test2() {
        Object o = getInstance();
    }

    public static StringBuffer stringBuffer(String s1, String s2) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(s1);
        buffer.append(s2);
        return buffer;
    }

    // 如果不希望上述方法的 StringBuffer 逃逸，可以这么改写
    public static String stringBuffer2(String s1, String s2) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(s1);
        buffer.append(s2);
        return buffer.toString();
    }
}
