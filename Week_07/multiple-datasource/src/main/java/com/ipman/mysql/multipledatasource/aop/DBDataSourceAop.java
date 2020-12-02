package com.ipman.mysql.multipledatasource.aop;


import com.ipman.mysql.multipledatasource.bean.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by ipipman on 2020/12/2.
 *
 * @version V1.0
 * @Package com.ipman.mysql.multipledatasource.aop
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/12/2 4:33 下午
 */
@Aspect
@SuppressWarnings("all")
@Component
public class DBDataSourceAop {

    //查询操作，指向Slave
    @Pointcut("!@annotation(com.ipman.mysql.multipledatasource.annotation.DBMaster) " +
            "&& (execution(* com.ipman.mysql.multipledatasource.dao..*.select*(..)) " +
            "|| execution(* com.ipman.mysql.multipledatasource.dao..*.get*(..)))")
    public void readPointcut() {
    }

    //修改操作，指向Master
    @Pointcut("@annotation(com.ipman.mysql.multipledatasource.annotation.DBMaster) " +
            "|| execution(* com.ipman.mysql.multipledatasource.dao..*.insert*(..)) " +
            "|| execution(* com.ipman.mysql.multipledatasource.dao..*.add*(..)) " +
            "|| execution(* com.ipman.mysql.multipledatasource.dao..*.update*(..)) " +
            "|| execution(* com.ipman.mysql.multipledatasource.dao..*.edit*(..)) " +
            "|| execution(* com.ipman.mysql.multipledatasource.dao..*.delete*(..)) " +
            "|| execution(* com.ipman.mysql.multipledatasource.dao..*.remove*(..))")
    public void writePointcut() {
    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }
}
