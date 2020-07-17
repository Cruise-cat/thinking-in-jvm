package com.cruise.thinking.in.jvm.classloader;

import sun.misc.Launcher;
import sun.security.ec.CurveDB;

import java.net.URL;
import java.security.Provider;

/**
 * 系统各个类加载器
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/14
 */
public class ClassLoaderDemo {

    public static void main(String[] args) {
        // 系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        // sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(systemClassLoader);

        // 扩展类加载器
        ClassLoader extClassLoader = systemClassLoader.getParent();
        // sun.misc.Launcher$ExtClassLoader@1540e19d
        System.out.println(extClassLoader);

        // 启动类加载器 无法获取
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        // null
        System.out.println(bootstrapClassLoader);
        // null Java 核心类库的类都是由 启动类加载器加载的
        System.out.println(String.class.getClassLoader());

        showLoadingClass();
    }

    /**
     * 显示不同的 ClassLoader 所加载的 Class 有哪些？
     */
    public static void showLoadingClass() {
        System.out.println("==============启动类加载器==============");
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urls) {
            System.out.println(url);
        }
        // 在上述路径随机找一个启动类加载器加载的类查看它的 ClassLoader
        ClassLoader classLoader = Provider.class.getClassLoader();
        System.out.println(classLoader);

        System.out.println("==============扩展类加载器==============");

        String dirs = System.getProperty("java.ext.dirs");
        for (String dir : dirs.split(";")) {
            System.out.println(dir);
        }
        // 在上述路径随机找一个扩展类加载器加载的类查看它的 ClassLoader
        classLoader = CurveDB.class.getClassLoader();
        System.out.println(classLoader);
    }
}
