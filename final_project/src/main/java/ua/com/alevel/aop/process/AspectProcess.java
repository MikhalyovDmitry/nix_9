package ua.com.alevel.aop.process;

import org.aspectj.lang.ProceedingJoinPoint;

public interface AspectProcess {

    Object process(ProceedingJoinPoint pjp) throws Throwable;
}
