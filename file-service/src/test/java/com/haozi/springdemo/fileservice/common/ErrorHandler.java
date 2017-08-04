package com.haozi.springdemo.fileservice.common;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
public class ErrorHandler
{
	private final static Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

	// 一般异常处理
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Uncatched Exception")
	@ResponseBody
	public String defaultErrorHandler(HttpServletResponse rsp, Exception e) throws Exception
	{
		logger.error("Uncatched Exception", e);
		return "false";
	}

	@ExceptionHandler(value = MultipartException.class)
	@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "Multipart Exception")
	@ResponseBody
	public String MultipartExceptionHandler(HttpServletResponse rsp, Exception e) throws Exception
	{
		logger.error("Multipart Exception", e);
		return "false";
	}
}
