package com.fzrj.starter.service.sandbox;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * AOP advicer to get log
 */
/**
 * @className: com.fzrj.starter.service.sandbox.MvcMethodLogAdvice
 * @description: 放开注释@Component来启用，所有添加了@RequestMapping的方法将打印日志
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/11/28 17:12
**/
@Aspect
@Component
public class MvcMethodLogAdvice {
    private static Logger logger = LoggerFactory.getLogger(MvcMethodLogAdvice.class);

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String url = request.getRequestURL().toString();
        Object args[] = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // join arguments.
//    LogUtils.saveAccessInfo(String.format("%s.%s", thisJoinPoint.getThis().getClass().getSimpleName(),
//    		thisJoinPointStaticPart.getSignature().getName()));
//    logger.debug("{}.{} : {} ",method.getDeclaringClass().getName(), method.getName(), StringUtils.join(args," ; "));
        logger.info("============接口url:{}", url);
        logger.info("============接口调用开始===========");

        logger.info("============类{};方法:{}===========", method.getDeclaringClass().getName().toString(), joinPoint.getSignature().getName());
    /*logger.info("Controller invoke: {} => {}.{}", MDC.get(LogConstants.LOG_CONTEXT_CALLER),
            thisJoinPoint.getThis().getClass().toString(), thisJoinPointStaticPart.getSignature().getName());*/
        if (args.length > 0) {
            logger.info("============>>>接口入参：Controller arguments: {}", new LoggingValueWrapper(args[0]));
        } else {
            logger.info("============>>>接口入参：Controller arguments: {}", new LoggingValueWrapper(null));
        }
        Object ret = joinPoint.proceed();
        logger.info("============>>>接口出参：Controller return: {}", new LoggingValueWrapper(ret));
        logger.info("============类:{};方法:{}===========", method.getDeclaringClass().getName().toString(), joinPoint.getSignature().getName());
        logger.info("============接口调用结束===========");


        return ret;
    }
}
