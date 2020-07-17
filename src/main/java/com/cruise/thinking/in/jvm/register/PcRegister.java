package com.cruise.thinking.in.jvm.register;

/**
 * 程序计数器示例
 * 命令行，当前目录：
 * javac PcRegister.java
 * javap -c PcRegister > PcRegister.txt
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
public class PcRegister {

    public int sum() {
        int a = 1;
        int b = 2;
        int c = a + b;
        return c;
    }

    public static void main(String[] args) {
        PcRegister registerDemo = new PcRegister();
        int sum = registerDemo.sum();
        System.out.println(sum);
    }
}
