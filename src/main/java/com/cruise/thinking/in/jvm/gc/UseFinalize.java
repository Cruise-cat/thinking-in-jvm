package com.cruise.thinking.in.jvm.gc;

/**
 * 使用 {@link Object#finalize()} 方法对垃圾回收的对象进行自救
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class UseFinalize {

    public static UseFinalize useFinalize = null;

    public void alive() {
        System.out.println("i am alive");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        useFinalize = this;
        System.out.println("execute finalize()");
    }

    public static void main(String[] args) throws Exception {
        useFinalize = new UseFinalize();

        // 对象第一次自救
        useFinalize = null;
        System.gc();
        // 由于finalize方法优先级比较低，休眠500ms
        Thread.sleep(500);
        if (useFinalize != null) {
            useFinalize.alive();
        } else {
            System.out.println("i am dead");
        }

        // 对象第二次自救，将失败
        useFinalize = null;
        System.gc();
        Thread.sleep(500);
        if (useFinalize != null) {
            useFinalize.alive();
        } else {
            System.out.println("i am dead");
        }
    }
}
