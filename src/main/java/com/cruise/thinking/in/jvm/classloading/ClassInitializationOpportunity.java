package com.cruise.thinking.in.jvm.classloading;

/**
 * 类初始化的时机
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/14
 */
public class ClassInitializationOpportunity {

    static {
        System.out.println("ClassInitializationOpportunity init");
    }

    /**
     * -XX:+TraceClassLoading 观察类的加载情况，加载并不是初始化
     * <p>调用当前类的 main 方法时会加载初始化当前类</p>
     *
     * @param args
     */
    public static void main(String[] args) throws ClassNotFoundException {
        // 会初始化类
        // 使用new关键字实例化对象
        // new User();
        // 读取类的静态变量
        // System.out.println(User.num);
        // 设置类的静态变量
        // User.num = 20;
        // 调用类的静态方法
        // User.show();
        // 使用java.lang.reflect包的方法对类型进行反射调用
        // Class.forName("com.cruise.thinking.in.jvm.classloading.User");
        // 初始化子类会初始化父类
        // new SuperUser();
        // 接口中定义了default方法，初始化其实现类必须先初始化接口
        // new User();
        // 调用引用类型的常量
        // System.out.println(User.number);
        // ==================================

        // 不会初始化

        // 读取一个类的静态常量
        // User类的常量Flag在编译阶段通过常量传播优化，已经将此常量的值true直接存储在ClassInitializationOpportunity类
        // 的常量池中，以后ClassInitializationOpportunity对常量User.FLAG的引用，实际都被转化为ClassInitializationOpportunity类
        // 对自身常量池的引用了。也就是说，实际上ClassInitializationOpportunity的Class文件之中并没有User类的符号引用入口，
        // 这两个类在编译成Class文件后就已不存在任何联系了。

        // System.out.println(User.FLAG);
        // 通过子类引用父类的静态变量,会初始化User,SuperUser被加载了但是没有被初始化
        // System.out.println(SuperUser.num);
        // 通过数组定义来引用类
        // User[] users = new User[10];
        // 访问 类.class
        // Class<User> userClass = User.class;
        // ClassLoader#loadClass
        // ClassLoader.getSystemClassLoader().loadClass("com.cruise.thinking.in.jvm.classloading.User");
        // Class.forName 第二个参数为 false
        // Class.forName("com.cruise.thinking.in.jvm.classloading.User",false,ClassLoader.getSystemClassLoader());
        // 调用 String 类型的常量
        // System.out.println(User.str);
        // 调用外部类的静态方法不会初始化内部类
        User.getInstance();
    }
}

class User implements Interest {

    public static int num = 10;

    public static final Integer number = 100;

    public static final String str = "text";

    public static final boolean FLAG = true;

    static {
        System.out.println("User init");
    }

    public static void show() {
    }

    static class UserHolder {
        private static final User user = new User();

        static {
            System.out.println("UserHolder init");
        }
    }

    public static User getInstance() {
        return UserHolder.user;
    }
}

class SuperUser extends User {
    static {
        System.out.println("SuperUser init");
    }
}

interface Interest {
    default void interest() {

    }
}