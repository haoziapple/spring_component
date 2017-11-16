package com.fzrj.starter.service.common.aop;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.fzrj.starter.service.common.ParamException;
import com.fzrj.starter.service.common.util.HttpContextUtil;
import com.fzrj.starter.service.common.util.IPUtil;
import com.google.gson.Gson;

/**
 * @className:com.fzrj.starter.service.common.aop.AdviceLog
 * @description:日志通知类，添加了#AddLog 的注解方法将统一由此类添加日志，若为Controller层方法会自动校验BindResult
 * @version:v1.0.0
 * @date:2017年8月15日 下午3:24:56
 * @author:WangHao
 */
@Aspect
@Component
public class AdviceLog {
    private static final Logger logger = LoggerFactory.getLogger(AdviceLog.class);

    // 使用threadLocal保存日志信息
    private static ThreadLocal<SysLogEntity> logHolder = new ThreadLocal<>();

    @Pointcut(value = "@annotation(com.fzrj.starter.service.common.aop.AddLog)")
    public void pointcut() {
        // 使用注解定义切面
    }

    @Before(value = "pointcut()")
    public void before(JoinPoint joinPoint) {
        SysLogEntity sysLog = this.initSysLog(joinPoint);
        logHolder.set(sysLog);
        logger.info("系统日志-req:{}", sysLog);
        // 校验请求参数
        validateReq(joinPoint);
    }

    @AfterReturning(value = "pointcut()", returning = "retValue")
    public void afterReturning(JoinPoint joinPoint, Object retValue) {
        SysLogEntity sysLog = logHolder.get();
        Gson gson = new Gson();
        String rsp = gson.toJson(retValue);
        sysLog.setRsp(rsp);
        sysLog.setTime(System.currentTimeMillis() - sysLog.getTime());
        logger.info("系统日志-rsp:{}", sysLog);
        logHolder.remove();
    }

    @AfterThrowing(value = "pointcut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        String stuff = signature.toString();
        String arguments = Arrays.toString(joinPoint.getArgs());
        logger.error(
                "发生异常\n方法: {} \n参数：{} \n方法签名: {} \n异常信息: {}",
                methodName, arguments, stuff, e.getMessage(), e);
    }

    /**
     * @param joinPoint
     * @return
     * @Description:初始化系统日志实体
     * @version:v1.0
     * @author:WangHao
     * @date:2017年4月25日 下午4:54:04
     */
    private SysLogEntity initSysLog(JoinPoint joinPoint) {
        SysLogEntity sysLog = new SysLogEntity();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // 注解值
        AddLog syslogAnno = method.getAnnotation(AddLog.class);
        if (syslogAnno != null) {
            // 注解上的描述
            sysLog.setOperation(syslogAnno.value());
        }

        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        // 请求的参数
        Object[] args = joinPoint.getArgs();
        Gson gson = new Gson();
        List<Object> paramList = new ArrayList<>();
        if (args.length > 0) {
            for (Object o : args) {
                if (o != null && o instanceof BindingResult) {
                    // BindingResult不打入日志
                } else {
                    paramList.add(o);
                }
            }
            String params = gson.toJson(paramList);
            sysLog.setParams(params);
        }

        // 获取request
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        // 设置IP地址
        sysLog.setIp(IPUtil.getIpAddr(request));
        sysLog.setCreateDate(new Date());
        sysLog.setTime(System.currentTimeMillis());

        return sysLog;
    }

    // Controller层校验请求参数，配合hibernate validator使用
    private void validateReq(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object o : args) {
            if (o != null && o instanceof BindingResult) {
                BindingResult result = (BindingResult) o;
                if (result.hasErrors()) {
                    throw new ParamException(result.getFieldError().getDefaultMessage());
                }
                break;
            }
        }
    }
}
