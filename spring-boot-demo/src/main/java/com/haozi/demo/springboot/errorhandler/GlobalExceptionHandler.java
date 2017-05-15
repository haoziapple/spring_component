package com.haozi.demo.springboot.errorhandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @className:com.haozi.demo.springboot.errorhandler.GlobalExceptionHandler
 * @description:统一异常处理类
 * @version:v1.0.0
 * @date:2017年5月15日 下午4:03:45
 * @author:WangHao
 */
@ControllerAdvice
public class GlobalExceptionHandler
{
	public static final String DEFAULT_ERROR_VIEW = "error";

	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
	}

	@ExceptionHandler(value = MyException.class)
	@ResponseBody
	public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception
	{
		ErrorInfo<String> r = new ErrorInfo<>();
		r.setMessage(e.getMessage());
		r.setCode(ErrorInfo.ERROR);
		r.setData("Some Data");
		r.setUrl(req.getRequestURL().toString());
		return r;
	}
}
