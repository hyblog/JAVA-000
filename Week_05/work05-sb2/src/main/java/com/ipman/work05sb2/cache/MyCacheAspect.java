package com.ipman.work05sb2.cache;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ipipman on 2020/11/20.
 *
 * @version V1.0
 * @Package com.ipman.work05sb2.cache
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/20 7:53 下午
 */
@Aspect
@Component
@Slf4j
public class MyCacheAspect {

    //缓存值
    private static ConcurrentHashMap<Method, Object> dataCache = new ConcurrentHashMap<>();
    //有效期
    private static ConcurrentHashMap<Method, Long> expireMapping = new ConcurrentHashMap<>();

    /**
     * 切入点
     */
    @Pointcut(value = "@annotation(com.ipman.work05sb2.cache.MyCacheAnn)")
    public void cachePointcut() {
    }

    /**
     * 缓存逻辑
     */
    @Around("cachePointcut()")
    public Object cacheAdvice(ProceedingJoinPoint point) throws Exception, Throwable {
        //获取切入点方法
        Method adviceMethod = toPointcutMethod(point);

        //是否设置有效期
        int initExpire = adviceMethod.getAnnotation(MyCacheAnn.class).expireSecond();
        if (initExpire == 0) {
            return point.proceed();
        }

        //获取缓存
        long nowMs = System.currentTimeMillis();
        Object result = null;
        if (expireMapping.containsKey(adviceMethod)) {
            Long expireSecond = expireMapping.get(adviceMethod);
            //缓存过期
            if ((nowMs - expireSecond) > 0) {
                result = point.proceed();
            } else {
                //缓存命中
                return dataCache.get(adviceMethod);
            }
        } else {
            result = point.proceed();
        }

        //保存缓存
        if (result != null) {
            dataCache.put(adviceMethod, result);
            expireMapping.put(adviceMethod, nowMs + initExpire * 1000);
        }
        return result;
    }

    /**
     * 获取切入点方法
     *
     * @param point
     * @return
     * @throws NoSuchMethodException
     */
    public Method toPointcutMethod(ProceedingJoinPoint point) throws NoSuchMethodException {
        String methodSignature = point.getSignature().getName();
        Object[] args = point.getArgs();
        Class<?>[] argTypes = new Class[point.getArgs().length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }
        return point.getTarget().getClass().getMethod(methodSignature, argTypes);
    }

}
