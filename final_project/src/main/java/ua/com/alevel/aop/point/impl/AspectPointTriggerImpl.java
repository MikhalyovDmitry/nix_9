package ua.com.alevel.aop.point.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;

import ua.com.alevel.aop.point.AspectPointTrigger;
import ua.com.alevel.aop.process.AspectProcess;

@Aspect
@Component
public class AspectPointTriggerImpl implements AspectPointTrigger {

    private final AspectProcess aspectProcess;

    public AspectPointTriggerImpl(AspectProcess aspectProcess) {
        this.aspectProcess = aspectProcess;
    }

    @Override
    @Pointcut("execution(* ua.com.alevel.service.impl.PLPServiceImpl.search(..))")
    public void pointcut() { }

    @Override
    @Around(value = "pointcut()")
    public Object doAspect(ProceedingJoinPoint pjp) throws Throwable {
        return aspectProcess.process(pjp);
    }
}
