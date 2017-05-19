package it.pkg.service.common;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import it.pkg.intf.bean.common.RspBean;

/**
 * @className:it.pkg.service.common.ErrorHandler
 * @description:公共的异常处理类，Controller抛出的异常在这里处理
 * @version:v0.1-SNAPSHOT
 * @date:2017年5月18日 上午9:06:17
 * @author:WangHao
 */
@ControllerAdvice
public class ErrorHandler
{
	private final static Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

	// 一般异常处理
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public RspBean defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception
	{
		RspBean rsp = new RspBean();
		rsp.setRspCode("SYS-ERR");
		if (e != null)
		{
			logger.error("系统异常", e);
			rsp.setRspMsg("系统异常");
			rsp.setData(e.toString());
		}
		return rsp;
	}

	// 接口参数异常处理
	@ExceptionHandler(value = ParamException.class)
	@ResponseBody
	public RspBean ParamErrorHandler(HttpServletRequest req, ParamException e) throws Exception
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
