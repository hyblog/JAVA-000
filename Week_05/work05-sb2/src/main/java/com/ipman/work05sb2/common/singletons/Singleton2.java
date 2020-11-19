package com.ipman.work05sb2.common.singletons;


/**
 * Created by ipipman on 2020/11/19.
 *
 * @version V1.0
 * @Package com.ipman.work05sb2.common.singletons
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/19 3:16 下午
 */
public class Singleton2 {


    // 懒汉线程安全模式，通过synchroized锁解决并发问题
    private static Singleton2 instance;
    private Singleton2(){
    }

    public synchronized static Singleton2 getInstance(){
        if (instance == null){
            instance = new Singleton2();
        }
        return instance;
    }
}
