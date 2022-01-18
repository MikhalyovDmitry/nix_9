package ua.com.alevel.aop.point;

import org.aspectj.lang.ProceedingJoinPoint;

public interface AspectPointTrigger {

    void pointcut();
    Object doAspect(ProceedingJoinPoint pjp) throws Throwable;
}
