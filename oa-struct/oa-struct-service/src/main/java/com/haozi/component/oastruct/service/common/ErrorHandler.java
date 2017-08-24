package com.haozi.component.oastruct.service.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haozi.component.oastruct.intf.bean.common.RspBean;

/**
 * @className:com.haozi.component.oastruct.service.common.ErrorHandler
 * @description:公共的异常处理类，Controller抛出的异常在这里处理，统一返回错误信息
 * @version:v1.0.0
 * @date:2017年5月18日 上午9:06:17
 * @author:WangHao
 */
@ControllerAdvice
public class ErrorHandler
{
	// 一般异常处理
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public RspBean defaultErrorHandler(HttpServletRequest req, Exception e)
	{
		RspBean rsp = new RspBean();
		rsp.setRspCode("SYS-ERR");
		if (e != null)
		{
			rsp.setRspMsg("系统异常");
			rsp.setData(e.toString());
		}
		return rsp;
	}

	// 接口参数异常处理
	@ExceptionHandler(value = ParamException.class)
	@ResponseBody
	public RspBean paramErrorHandler(HttpServletRequest req, ParamException e)
	{
		RspBean rsp = new RspBean();
		rsp.setRspCode("PARAM-ERR");
		if (e != null)
		{
			rsp.setRspMsg(e.getMessage());
		}
		return rsp;
	}
}
