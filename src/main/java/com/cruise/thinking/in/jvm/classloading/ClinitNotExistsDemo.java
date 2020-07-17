package com.cruise.thinking.in.jvm.classloading;

/**
 * 虚拟机不自动生成 <Clinit> 的情况<br>
 * 通过 jclasslib Bytecode Viewer 插件观察不存在 <clinit> 方法
 * javap -p -v ClinitNotExistsDemo 不存在 static{}
 * <p>如果没有静态块或者没有静态变量的初始化操作不会生成类构造器<Clinit></p>
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/14
 */
public class ClinitNotExistsDemo {

    private int a = 1;
}
