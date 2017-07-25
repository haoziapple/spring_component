package com.fzrj.starter.service.common.aop;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.fzrj.starter.service.common.ParamException;
import com.fzrj.starter.service.common.util.HttpContextUtil;
import com.fzrj.starter.service.common.util.IPUtil;
import com.google.gson.Gson;

@Aspect
@Component
public class AdviceLog
{
	private final static Logger logger = LoggerFactory.getLogger(AdviceLog.class);

	@Pointcut(value = "@annotation(com.fzrj.starter.service.common.aop.AddLog)")
	public void pointcut()
	{

	}

	@Before(value = "pointcut()")
	public void before(JoinPoint joinPoint)
	{
		SysLogEntity sysLog = this.initSysLog(joinPoint);
		logger.info("系统日志-req:{}", sysLog);
		// 校验请求参数
		validateReq(joinPoint);
	}

	@AfterReturning(value = "pointcut()", returning = "retValue")
	public void afterReturning(JoinPoint joinPoint, Object retValue)
	{
		SysLogEntity sysLog = this.initSysLog(joinPoint);
		Gson gson = new Gson();
		String rsp = gson.toJson(retValue);
		sysLog.setRsp(rsp);
		logger.info("系统日志-rsp:{}", sysLog);
	}

	/**
	 * @Description:初始化系统日志实体
	 * @param joinPoint
	 * @return
	 * @version:v1.0
	 * @author:WangHao
	 * @date:2017年4月25日 下午4:54:04
	 */
	private SysLogEntity initSysLog(JoinPoint joinPoint)
	{
		SysLogEntity sysLog = new SysLogEntity();
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		// 注解值
		AddLog syslogAnno = method.getAnnotation(AddLog.class);
		if (syslogAnno != null)
		{
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
        if (args.length > 0) {
            String params = gson.toJson(args[0]);
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
        if (args.length > 1 && args[1] != null && args[1] instanceof BindingResult) {
            BindingResult result = (BindingResult) args[1];
            if (result.hasErrors())
                throw new ParamException(result.getFieldError().getDefaultMessage());
        } else if (args.length > 0 && args[0] instanceof BindingResult) {
            BindingResult result = (BindingResult) args[0];
            if (result.hasErrors())
                throw new ParamException(result.getFieldError().getDefaultMessage());
        }
    }
}
