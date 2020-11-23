package com.ipman.work06java8.gof;

import com.ipman.work06java8.gof.factory.AbRouJiaMo;
import com.ipman.work06java8.gof.factory.SimpleRouJiaMo;
import com.ipman.work06java8.gof.singleton.Singleton;

/**
 * Created by ipipman on 2020/11/23.
 *
 * @version V1.0
 * @Package com.ipman.work06java8.gof
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/23 9:42 下午
 */
public class TestDemo {

    public static void main(String[] args) {

        //单例模式主要是为了避免因为创建了多个实例造成资源的浪费，且多个实例由于多次调用容易导致结果出现错误，而使用单例模式能够保证整个应用中有且只有一个实例。
        System.out.println("单例模式：" + Singleton.getInstance());


        //定义一个创建对象的接口，但由子类决定要实例化的类是哪一个。工厂方法模式把类实例化的过程推迟到子类。
        SimpleRouJiaMo simpleRouJiaMo = new SimpleRouJiaMo();
        AbRouJiaMo factory = simpleRouJiaMo.createRowJiaMo("La");
        factory.fire();
        factory.pack();
        factory.prepare();

    }
}
