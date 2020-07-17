package com.cruise.thinking.in.jvm.classloader;

import java.io.*;

/**
 * 自定义类加载器
 * <p>测试从 D 盘读取一个 .class 文件到当前系统内并生成一个对象调用,将对象进行输出</p>
 *
 * <pre class="code">
 * package com.cruise.demo;
 *
 * public class Hello {
 *
 *    private int a = 2;
 *    private static String b = "hello";
 *
 *    public int getA(){
 * 	    return a;
 *    }
 *
 *    @Override
 *    public String toString(){
 * 	    System.out.println(a);
 * 	    System.out.println(b);
 * 	    return b;
 *    }
 * }
 * </pre>
 *
 * <p>javac Hello.java</p>
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/14
 */
public class CustomizedClassLoader extends ClassLoader {

    private String customizedPath;

    private static final String SUFFIX = ".class";

    public CustomizedClassLoader(String customizedPath) {
        super();
        this.customizedPath = customizedPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            String fileName = name.substring(name.lastIndexOf(".") + 1) + SUFFIX;
            byte[] bytes = getClassFromCustomPath(fileName);
            if (bytes == null || bytes.length == 0) {
                throw new FileNotFoundException(name);
            }
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name);
        }

    }

    private byte[] getClassFromCustomPath(String name) throws IOException {
        FileInputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(new File(this.customizedPath.concat(name)));
            outputStream = new ByteArrayOutputStream();
            // 设置缓冲，这样便于传输，大大提高传输效率
            byte[] flush = new byte[1024 * 10];
            // 设置每次传输的个数,若没有缓冲的数据大，则返回剩下的数据，没有数据返回-1
            int len = -1;
            while ((len = inputStream.read(flush)) != -1) {
                outputStream.write(flush, 0, len);//每次读取len长度数据后，将其写出
            }
            // 刷新管道数据
            outputStream.flush();
            // 返回获得的字节数组
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw e;
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        CustomizedClassLoader classLoader = new CustomizedClassLoader("D:\\");
        Class<?> loadClass = classLoader.loadClass("com.cruise.demo.Hello");
        Object o = loadClass.newInstance();
        System.out.println(o);
    }
}
