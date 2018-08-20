package com.jova.wrid.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * select 和 query 主动切换到从库查询
 * @author zhangqw
 * @date 2018-08-14
 */
@Aspect
@Component
public class DataSourceAspect {

    @Pointcut("execution(* com.jova.wrid.dao.*.select*(..))")
    public void select(){
    }

    @Pointcut("execution(* com.jova.wrid.dao.*.query*(..))")
    public void query(){
    }

    /**
     *  查询切换从库
     *  查询结束切换到主库
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("select() || query()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        DatabaseContextHolder.setDatabaseType(DatabaseType.slaveDb);
        Object object =  point.proceed();
        DatabaseContextHolder.setDatabaseType(DatabaseType.masterDb);
        return object;
    }


}
