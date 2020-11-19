package com.ipman.work05sb2.common.singletons;

/**
 * Created by ipipman on 2020/11/19.
 *
 * @version V1.0
 * @Package com.ipman.work05sb2.common.singletons
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/19 2:51 下午
 */
public class Singleton1 {

    //懒汉，线程不安全模式，多线程不能正常工作
    private static Singleton1 instance;
    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }
}
