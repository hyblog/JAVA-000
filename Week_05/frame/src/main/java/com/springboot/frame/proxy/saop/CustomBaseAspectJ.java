package com.springboot.frame.proxy.saop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


/**
 * Created by ipipman on 2020/11/17.
 *
 * @version V1.0
 * @Package com.springboot.frame.proxy.aspectjs
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/17 6:26 下午
 */
@Aspect
@SuppressWarnings("all")
@Component
public class CustomBaseAspectJ {

    //切入点
    @Pointcut("@annotation(com.springboot.frame.proxy.saop.CustomAspectJAnn)")
//    @Pointcut("execution(public * com.springboot.frame.proxy.*.*(..))")
    public void customPointcut() {
        System.out.println(1);
    }

    //切入点之前的逻辑
    @Before("customPointcut()")
    public void beforeAdvice() {
        System.out.println("PersonProxy.doAdvice() start ....");
    }

    //切入点之后的逻辑
    @After("customPointcut()")
    public void afterAdvice() {
        System.out.println("PersonProxy.doAdvice() end ....");
    }

    //写入点之后异常处理逻辑
    @AfterThrowing(value = "customPointcut()", throwing = "e")
    public void afterThrowingAdvice(JoinPoint joinPoint, Throwable e) {
        e.printStackTrace();
    }
}
