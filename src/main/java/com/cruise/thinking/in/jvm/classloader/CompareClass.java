package com.cruise.thinking.in.jvm.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * 比较使用不同的 {@link ClassLoader} 加载的类是否相等
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/14
 */
public class CompareClass {

    /**
     * <p>object对象与类com.cruise.thinking.in.jvm.classloader.CompareClass做所属类型检查的时候返回false</p>
     * 因为Java虚拟机中同时存在了两个CompareClass类，一个是由虚拟机的应用程序类加载器所加载的，另一个由自定义的类加载器加载的，
     * 虽然它们都来自同一个Class文件，但在Java虚拟机中仍然是两个互相独立的类，做对象所属类型检查时的结果自然为false
     *
     * @param args
     * @throws ClassNotFoundException,IllegalAccessException,InstantiationException
     */
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader myClassLoader = new MyClassLoader();
        Object object = myClassLoader.loadClass("com.cruise.thinking.in.jvm.classloader.CompareClass").newInstance();
        System.out.println(object);

        Object instance = ClassLoader.getSystemClassLoader().loadClass("com.cruise.thinking.in.jvm.classloader.CompareClass").newInstance();

        // false
        System.out.println(object.getClass().equals(instance.getClass()));

        // false
        System.out.println(object.getClass() == instance.getClass());

        // false
        System.out.println(object instanceof CompareClass);

        // false
        System.out.println(object == instance);

    }

    // 自定义类加载器
    static class MyClassLoader extends ClassLoader {
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            try {
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream is = getClass().getResourceAsStream(fileName);
                if (is == null) {
                    return super.loadClass(name);
                }
                byte[] b = new byte[is.available()];
                is.read(b);
                return defineClass(name, b, 0, b.length);
            } catch (IOException e) {
                throw new ClassNotFoundException(name);
            }
        }
    }
}
