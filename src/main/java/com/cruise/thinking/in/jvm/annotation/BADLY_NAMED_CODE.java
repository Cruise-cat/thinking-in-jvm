package com.cruise.thinking.in.jvm.annotation;

/**
 * 包含了多处不规范命名的代码样例
 * 进入  /d/workspaces/github/thinking-in-jvm/target/classes/ 目录执行
 * javac -encoding UTF-8 -processor com.cruise.thinking.in.jvm.annotation.NameCheckProcessor com/cruise/thinking/in/jvm/annotation/BADLY_NAMED_CODE.java
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/17
 */
public class BADLY_NAMED_CODE {

    enum colors {
        red, blue, green;
    }

    static final int _FORTY_TWO = 42;
    public static int NOT_A_CONSTANT = _FORTY_TWO;

    protected void BADLY_NAMED_CODE() {
        return;
    }

    public void NOTcamelCASEmethodNAME() {
        return;
    }
}
