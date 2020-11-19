package com.ipman.work05sb2.common.singletons;

/**
 * Created by ipipman on 2020/11/19.
 *
 * @version V1.0
 * @Package com.ipman.work05sb2.common.singletons
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/19 3:38 下午
 */
public class Singleton3 {

    private static Singleton3 instance = null;
    //饿汉方式，在classloader类装载期完成初始化，避免线程安全问题
    static {
        instance = new Singleton3();
    }
    private Singleton3(){

    }
    public static Singleton3 getInstance(){
        return instance;
    }
}
