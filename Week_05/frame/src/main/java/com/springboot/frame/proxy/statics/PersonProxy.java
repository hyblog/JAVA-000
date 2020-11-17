package com.springboot.frame.proxy.statics;

/**
 * Created by ipipman on 2020/11/17.
 *
 * @version V1.0
 * @Package com.springboot.frame.proxy.statics
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/17 3:15 下午
 */
public class PersonProxy {

    private IPerson iPerson;

    public PersonProxy(IPerson iPerson) {
        this.iPerson = iPerson;
    }

    //代理增强
    public void doAdvice() {
        System.out.println("PersonProxy.doAdvice() start ....");
        iPerson.doing();
        System.out.println("PersonProxy.doAdvice() end ....");
    }


    //静态代理（在编译期，切面直接以字节码的形式编译到目标字节码文件中）
    public static void main(String[] args) {
        PersonProxy personProxy = new PersonProxy(new Person());
        personProxy.doAdvice();
    }
}
