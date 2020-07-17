package com.cruise.thinking.in.jvm.classloader;

/**
 * 获取 {@link ClassLoader} 的几种方式
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/14
 */
public class ObtainClassLoaderWays {
    public static void main(String[] args) throws ClassNotFoundException {
        // 1.获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
        // 2.获取线程上下文类加载器
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);
        // 3.clazz.getClassLoader()
        ClassLoader classLoader = ObtainClassLoaderWays.class.getClassLoader();
        System.out.println(classLoader);
        // 反射
        ClassLoader loader = Class.forName("com.cruise.thinking.in.jvm.classloader.ObtainClassLoaderWays").getClassLoader();
        System.out.println(loader);
    }
}
