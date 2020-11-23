package com.ipman.work06java8.gof.singleton;

/**
 * Created by ipipman on 2020/11/23.
 *
 * @version V1.0
 * @Package com.ipman.work06java8.gof.singleton
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/23 9:43 下午
 */
public class Singleton {

    //单例模式主要是为了避免因为创建了多个实例造成资源的浪费，且多个实例由于多次调用容易导致结果出现错误，而使用单例模式能够保证整个应用中有且只有一个实例。
    private static Singleton instance;

    public static Singleton getInstance() {
        if (instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}
