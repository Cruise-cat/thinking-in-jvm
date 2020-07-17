package com.cruise.thinking.in.jvm.engine;

/**
 * 方法调用 —— 静态解析
 * <p>通过 jclasslib Bytecode Viewer 插件观察</p>
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/15
 */
public class StaticResolution {

    public static void main(String[] args) {
        // 使用 invokestatic 指令调用
        // 并且此方法引用存在于 Constant Pool 中
        StaticResolution.show();

        // 使用 invokespecial 指令调用实例构造器方法
        StaticResolution invoke = new StaticResolution();

        // 使用 invokevirtual 指令调用 final 修饰的方法
        // 并且此方法引用存在于 Constant Pool 中
        invoke.run();
    }

    public static void show() {

    }

    public final void run() {

    }
}
