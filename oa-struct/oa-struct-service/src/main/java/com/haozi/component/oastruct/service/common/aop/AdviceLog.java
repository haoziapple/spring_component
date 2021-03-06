package com.haozi.component.oastruct.service.common.aop;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.haozi.component.oastruct.service.common.ParamException;
import com.haozi.component.oastruct.service.common.util.HttpContextUtil;
import com.haozi.component.oastruct.service.common.util.IPUtil;
import com.google.gson.Gson;

/**
 * @className:com.haozi.component.oastruct.service.common.aop.AdviceLog
 * @description:日志通知类，添加了#AddLog 的注解方法将统一由此类添加日志，若为Controller层方法会自动校验BindResult
 * @version:v1.0.0
 * @date:2017年8月15日 下午3:24:56
 * @author:WangHao
 */
@Aspect
@Component
public class AdviceLog
{
	private static final Logger logger = LoggerFactory.getLogger(AdviceLog.class);

	@Pointcut(value = "@annotation(com.haozi.component.oastruct.service.common.aop.AddLog)")
	public void pointcut()
	{
		// 使用注解定义切面
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

	@AfterThrowing(value = "pointcut()", throwing = "e")
	public void afterThrowing(JoinPoint joinPoint, Throwable e)
	{
		Signature signature = joinPoint.getSignature();
		String methodName = signature.getName();
		String stuff = signature.toString();
		String arguments = Arrays.toString(joinPoint.getArgs());
		logger.error(
				"have caught exception in method: {} with arguments {} \nand the full toString: {} \nthe exception is: {}",
				methodName, arguments, stuff, e.getMessage(), e);
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
		if (args.length > 0)
		{
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
	private void validateReq(JoinPoint joinPoint)
	{
		Object[] args = joinPoint.getArgs();
		if (args.length > 1 && args[1] != null && args[1] instanceof BindingResult)
		{
			BindingResult result = (BindingResult) args[1];
			if (result.hasErrors())
				throw new ParamException(result.getFieldError().getDefaultMessage());
		}
		else if (args.length > 0 && args[0] instanceof BindingResult)
		{
			BindingResult result = (BindingResult) args[0];
			if (result.hasErrors())
				throw new ParamException(result.getFieldError().getDefaultMessage());
		}
	}
}
