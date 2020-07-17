package com.cruise.thinking.in.jvm.oom;

/**
 * {@link StackOverflowError} 示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/16
 */
public class StackOverflowErrorDemo {

    int stackLength = 1;

    public void stack() {
        stackLength++;
        stack();
    }

    /**
     * VM args: -Xss128k
     *
     * @param args
     */
    public static void main(String[] args) {
        StackOverflowErrorDemo stackOOMTest = new StackOverflowErrorDemo();
        try {
            stackOOMTest.stack();
        } catch (Throwable e) {
            System.out.println(stackOOMTest.stackLength);
            e.printStackTrace();
        }
    }
}
