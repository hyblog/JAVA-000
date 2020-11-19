package com.ipman.work05sb2.common.singletons;

/**
 * Created by ipipman on 2020/11/19.
 *
 * @version V1.0
 * @Package com.ipman.work05sb2.common.singletons
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/19 3:48 下午
 */
public class Singleton4 {
    // 使用classload机制保证初始化时只有一个线程
    // 类被装载了，instance不被初始化，初始化再第一次调用
    private static class Singletion4Holder {
        private static final Singleton4 INSTANCE = new Singleton4();
    }
    private Singleton4(){
    }

    public static final Singleton4 getInstance(){
        return Singletion4Holder.INSTANCE;
    }
}
