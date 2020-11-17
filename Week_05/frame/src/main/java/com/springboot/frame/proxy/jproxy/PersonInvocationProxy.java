package com.springboot.frame.proxy.jproxy;

import com.springboot.frame.proxy.statics.IPerson;
import com.springboot.frame.proxy.statics.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by ipipman on 2020/11/17.
 *
 * @version V1.0
 * @Package com.springboot.frame.proxy.invo
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/17 4:10 下午
 */
public class PersonInvocationProxy implements InvocationHandler {

    private Object delegate;

    public Object bind(Object delegate) {
        this.delegate = delegate;
        return Proxy.newProxyInstance(delegate.getClass().getClassLoader(), delegate.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        try {
            System.out.println("PersonProxy.doAdvice() start ....");
            result = method.invoke(delegate, args);
            System.out.println("PersonProxy.doAdvice() end ....");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // JDK1.3后引入动态代理机制，我们可以在运行期间动态创建代理类
    // 使用动态代理实现AOP需要有四个角色：被代理的类，被代理类的接口，织入器，和InvocationHandler
    // 而织入器使用接口反射机制生成一个代理类，然后在这个代理类中织入代码
    // 被代理的类是AOP里所说的目标，InvocationHandler是切面，它包含了Advice和Pointcut
    public static void main(String[] args) {
        PersonInvocationProxy proxy = new PersonInvocationProxy();
        IPerson iPerson = (IPerson) proxy.bind(new Person());
        iPerson.doing();
    }

}
