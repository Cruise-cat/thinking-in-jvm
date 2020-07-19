package com.cruise.thinking.in.jvm.optimize;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Java 语法糖
 * javap -v -p JavaGrammarSugar
 * @author Cruise
 * @version 1.0
 * @since 2020/7/19
 */
public class JavaGrammarSugar {

    // 1. 没有显示指定构造器，编译器会添加默认的构造器
    /**
     *  public com.cruise.thinking.in.jvm.optimize.JavaGrammarSugar();
     *     descriptor: ()V
     *     flags: ACC_PUBLIC
     *     Code:
     *       stack=1, locals=1, args_size=1
     *          0: aload_0
     *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
     *          4: return
     *       LineNumberTable:
     *         line 10: 0
     *       LocalVariableTable:
     *         Start  Length  Slot  Name   Signature
     *             0       5     0  this   Lcom/cruise/thinking/in/jvm/optimize/JavaGrammarSugar;
     */


    /**
     * 2.自动装箱拆箱
     *  public void test1();
     *     descriptor: ()V
     *     flags: ACC_PUBLIC
     *     Code:
     *       stack=1, locals=3, args_size=1
     *          0: iconst_1
     *          1: invokestatic  #2                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
     *          4: astore_1
     *          5: aload_1
     *          6: invokevirtual #3                  // Method java/lang/Integer.intValue:()I
     *          9: istore_2
     *         10: return
     *       LineNumberTable:
     *         line 31: 0
     *         line 32: 5
     *         line 33: 10
     *       LocalVariableTable:
     *         Start  Length  Slot  Name   Signature
     *             0      11     0  this   Lcom/cruise/thinking/in/jvm/optimize/JavaGrammarSugar;
     *             5       6     1     a   Ljava/lang/Integer;
     *            10       1     2     b   I
     */
    public void test1(){
        // 自动装箱 Integer.valueOf
        Integer a = 1;
        // 自动拆箱 Integer.intValue
        int b = a;
    }

    /**
     *  3. 泛型擦除
     *  public void test2();
     *     descriptor: ()V
     *     flags: ACC_PUBLIC
     *     Code:
     *       stack=2, locals=3, args_size=1
     *          0: new           #4                  // class java/util/ArrayList
     *          3: dup
     *          4: invokespecial #5                  // Method java/util/ArrayList."<init>":()V
     *          7: astore_1
     *          8: aload_1
     *          9: iconst_1
     *         10: invokestatic  #2                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
     *         13: invokeinterface #6,  2            // InterfaceMethod java/util/List.add:(Ljava/lang/Object;)Z
     *         18: pop
     *         19: aload_1
     *         20: iconst_0
     *         21: invokeinterface #7,  2            // InterfaceMethod java/util/List.get:(I)Ljava/lang/Object;
     *         26: checkcast     #8                  // class java/lang/Integer
     *         29: invokevirtual #3                  // Method java/lang/Integer.intValue:()I
     *         32: istore_2
     *         33: return
     *       LineNumberTable:
     *         line 65: 0
     *         line 66: 8
     *         line 67: 19
     *         line 68: 33
     *       LocalVariableTable:
     *         Start  Length  Slot  Name   Signature
     *             0      34     0  this   Lcom/cruise/thinking/in/jvm/optimize/JavaGrammarSugar;
     *             8      26     1  list   Ljava/util/List;
     *            33       1     2     a   I
     *       LocalVariableTypeTable:
     *         Start  Length  Slot  Name   Signature
     *             8      26     1  list   Ljava/util/List<Ljava/lang/Integer;>;
     */
    public void test2(){
        // 泛型擦除 但是在 LocalVariableTypeTable 中仍然保存了泛型的信息
        // 等价于
        // List list = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        // 等价于 list.add(Object)
        list.add(1);
        // 等价于 int a = ((Integer)list.get(0)).intValue()
        int a = list.get(0);
    }

    public Set<Integer> test3(List<String> list, Map<String,Integer> map){
        return null;
    }

    /**
     * 4.
     * 获取方法中泛型类型
     * @throws NoSuchMethodException
     */
    public static void test4() throws NoSuchMethodException {
        Method method = JavaGrammarSugar.class.getMethod("test3", List.class, Map.class);
        Type[] types = method.getGenericParameterTypes();
        for (Type type : types) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type rawType = parameterizedType.getRawType();
                System.out.println("原始类型："+rawType);
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                for (int i = 0; i < actualTypeArguments.length; i++) {
                    System.out.printf("泛型参数[%s]:%s %n",i,actualTypeArguments[i]);
                }
            }
        }
    }

    /**
     * 5. 变长参数
     * public void test5(java.lang.String...);
     *     descriptor: ([Ljava/lang/String;)V
     *     flags: ACC_PUBLIC, ACC_VARARGS
     *     Code:
     *       stack=0, locals=2, args_size=2
     *          0: return
     *       LineNumberTable:
     *         line 143: 0
     *       LocalVariableTable:
     *         Start  Length  Slot  Name   Signature
     *             0       1     0  this   Lcom/cruise/thinking/in/jvm/optimize/JavaGrammarSugar;
     *             0       1     1  args   [Ljava/lang/String;
     * @param args
     */
    public void test5(String... args){
        // 变长参数
        // 等价于 test5(String[] args)
        // 如果调用 test5() 没有传参数相当于 test5(new String[]{}) 不会传一个 null 值参数
    }

    /**
     * 6.foreach
     */
    public void test6(){
        // 数组 foreach
        int[] arr = {1,2,3};
        for (int e : arr) {
            System.out.println(e);
        }
        // 等价于
//        int[] arr = new int[]{1,2,3};
//        for (int i = 0; i < arr.length; i++) {
//            int e = arr[i];
//            System.out.println(e);
//        }
        // 集合 foreach
//        List<Integer> list = Arrays.asList(1,2,3);
//        for (Integer e : list) {
//            System.out.println(e);
//        }
        // 等价于
        List list = Arrays.asList(1,2,3);
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer e = (Integer) iterator.next();
            System.out.println(e);
        }
    }

    /**
     * 7. switch 字符串
     * @param str
     */
    public void test7(String str){
        //
        switch (str) {
            case "java":
                System.out.println("j");
                break;
            case "python":
                System.out.println("p");
                break;
        }
        // 等价于
        byte x = -1;
        switch (str.hashCode()) {
            case 3254818: // java 的 hashCode
                if (str.equals("java")) { // hash 冲突时进一步通过 equals 方法比较
                    x = 0;
                }
                break;
            case -973197092: // python 的 hashCode
                if (str.equals("python")) {
                    x = 1;
                }
                break;
        }
        switch (x) {
            case 0:
                System.out.println("j");
                break;
            case 1:
                System.out.println("p");
                break;
        }
        // 所以这也是 switch 字符串不能为 null 的原因
    }

    // 8. switch 枚举
    public void test8(Sex sex){
        switch (sex) {
            case MALE:
                System.out.println("男"); break;
            case FEMALE:
                System.out.println("女"); break;
        }
        // 编译后相当于
        /**
         * 定义一个合成类（仅 jvm 使用，对我们不可见）
         * 用来映射枚举的 ordinal 与数组元素的关系
         * 枚举的 ordinal 表示枚举对象的序号，从 0 开始
         * 即 MALE 的 ordinal()=0，FEMALE 的 ordinal()=1
        static class $MAP {
            // 数组大小即为枚举元素个数，里面存储case用来对比的数字
            static int[] map = new int[2];

            static {
                map[Sex.MALE.ordinal()] = 1;
                map[Sex.FEMALE.ordinal()] = 2;
            }
        }

        public static void foo(Sex sex) {
            int x = $MAP.map[sex.ordinal()];
            switch (x) {
                case 1:
                    System.out.println("男");
                    break;
                case 2:
                    System.out.println("女");
                    break;
            }
        }*/
    }

    /**
     * 10. try-with-resources
     * JDK 7 开始新增了对需要关闭的资源处理的特殊语法 try-with-resources:
     * 其中资源对象需要实现 AutoCloseable 接口，例如 InputStream 、 OutputStream 、
     * Connection 、 Statement 、 ResultSet 等接口都实现了 AutoCloseable ，
     * 使用 try-withresources 可以不用写 finally 语句块，编译器会帮助生成关闭资源代码
     */
    public void test9 (){
        try(FileOutputStream outputStream = new FileOutputStream(new File("1.txt"))){

        } catch (IOException e) {

        }
        // 等价于
/*        try {
            InputStream is = new FileInputStream("d:\\1.txt");
            System.out.println(is);
        } catch (Throwable e1) {
            // t 是我们代码出现的异常
            t = e1;
            throw e1;
        } finally {
            // 判断了资源不为空
            if (is != null) {
                // 如果我们代码有异常
                if (t != null) {
                    try {
                        is.close();
                    } catch (Throwable e2) {
                        // 如果 close 出现异常，作为被压制异常添加 为了防止异常信息的丢失
                        t.addSuppressed(e2);
                    }
                } else {
                    // 如果我们代码没有异常，close 出现的异常就是最后 catch 块中的 e
                    is.close();
                }
            }
        }*/
    }

    // 11.匿名内部类
    public void test10 (final int x){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("ok:" + x);
            }
        };
        // 等价于
        // 额外生成的类
//        final class Candy11$1 implements Runnable {
//            int val$x;
//            Candy11$1(int x) {
//                this.val$x = x;
//            }
//            public void run() {
//                System.out.println("ok:" + this.val$x);
//            }
//        }
//        // 调用
//        Runnable runnable = new Candy11$1(x);
        //这同时解释了为什么匿名内部类引用局部变量时，局部变量必须是 final 的：因为在创建
        //Candy11$1 对象时，将 x 的值赋值给了 Candy11$1 对象的 val
        //属性 ，所以 x 不应该再发生变化了 ，如果变化，那么 x 属性没有机会再跟着一起变化
    }

    public static void main(String[] args) throws NoSuchMethodException {
        test4();
        System.out.println("java".hashCode());
        System.out.println("python".hashCode());
        new Child().test();
    }

    // 9 枚举
    private static enum Sex {
        MALE,FEMALE;
    }
    // 枚举类编译后相当于
/*    public final class Sex extends Enum<Sex> {
        public static final Sex MALE;
        public static final Sex FEMALE;
        private static final Sex[] $VALUES;

        static {
            MALE = new Sex("MALE", 0);
            FEMALE = new Sex("FEMALE", 1);
            $VALUES = new Sex[]{MALE, FEMALE};
        }

        private Sex(String name, int ordinal) {
            super(name, ordinal);
        }
        public static Sex[] values() {
            return $VALUES.clone();
        }
        public static Sex valueOf(String name) {
            return Enum.valueOf(Sex.class, name);
        }
    }*/

    private static class Parent {
        public Number getNumber(){
            return null;
        }
    }

    /**
     * 11. 方法重写
     * 方法重写时的桥接方法
     * 我们都知道，方法重写时对返回值分两种情况：
     * 父子类的返回值完全一致
     * 子类返回值可以是父类返回值的子类（比较绕口，见下面的例子）
     */
    private static class Child extends Parent {
        @Override
        public Integer getNumber() {
            return null;
        }

        // 此方法才是真正重写了父类 public Number m() 方法
//        public synthetic bridge Number getNumber() {
//            // 调用 public Integer m()
//            return getNumber();
//        }
        // 其中桥接方法比较特殊，仅对 java 虚拟机可见，并且与原来的 public Integer getNumber() 没有命名冲突，可以
        //用下面反射代码来验证：
        public void test(){
            for (Method m : Child.class.getDeclaredMethods()) {
                System.out.println(m);
            }
            //public java.lang.Number com.cruise.thinking.in.jvm.optimize.JavaGrammarSugar$Child.getNumber()
            //public java.lang.Integer com.cruise.thinking.in.jvm.optimize.JavaGrammarSugar$Child.getNumber()
        }
    }
}
