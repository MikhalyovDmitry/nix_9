package ua.com.alevel.aop.process.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import ua.com.alevel.aop.process.AspectProcess;

import ua.com.alevel.service.SearchBookInfoService;
import ua.com.alevel.util.WebUtil;

import java.util.Map;

@Component
public class AspectProcessImpl implements AspectProcess {

    private final SearchBookInfoService searchBookInfoService;

    public AspectProcessImpl(SearchBookInfoService searchBookInfoService) {
        this.searchBookInfoService = searchBookInfoService;
    }

    @Override
    public Object process(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        if (args != null) {
            if (args[0] instanceof Map<?,?>) {
                Map<String, Object> queryMap = (Map<String, Object>) args[0];
                Long publisherId = (Long) queryMap.get(WebUtil.PUBLISHER_PARAM);
                if (publisherId != null) {
                    searchBookInfoService.process(WebUtil.PUBLISHER_PARAM, publisherId);
                }
            }
        }
        return pjp.proceed();
    }
}
