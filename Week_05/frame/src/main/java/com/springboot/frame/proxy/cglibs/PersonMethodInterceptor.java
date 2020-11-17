package com.springboot.frame.proxy.cglibs;

import com.springboot.frame.proxy.statics.Person;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


/**
 * Created by ipipman on 2020/11/17.
 *
 * @version V1.0
 * @Package com.springboot.frame.proxy.cglibs
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/17 4:48 下午
 */
public class PersonMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result = null;
        System.out.println("PersonProxy.doAdvice() start ....");
        try {
            result = methodProxy.invokeSuper(method, objects);
        }catch (Exception e){ }
        System.out.println("PersonProxy.doAdvice() end ....");
        return result;
    }

    // CGLIB解决了动态代理的难题，它通过生成目标类子类的实现方式来实现代理，而不是接口
    // CGLIB是一个强大的高性能代码生成包，其在运行期生成被代理对象的子类，并重写了被代理对象的所有方法，从而实现代理对象
    public static Person getProxyInstance(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Person.class);

        enhancer.setCallback(new PersonMethodInterceptor());
        return (Person) enhancer.create();
    }

    public static void main(String[] args) {
        Person person = getProxyInstance();
        person.doing();
    }
}
