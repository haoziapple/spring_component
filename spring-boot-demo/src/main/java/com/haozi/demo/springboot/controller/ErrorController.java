package com.haozi.demo.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haozi.demo.springboot.errorhandler.MyException;

/**
 * @className:com.haozi.demo.springboot.controller.ErrorController
 * @description:测试公共异常的处理
 * @version:v1.0.0
 * @date:2017年5月15日 下午4:12:03
 * @author:WangHao
 */
@RestController
public class ErrorController
{
	private final static Logger logger = LoggerFactory.getLogger(ErrorController.class);

	@RequestMapping("/errortest")
	public void error()
	{
		throw new RuntimeException("test error");
	}

	@RequestMapping("myerror")
	public void myError() throws MyException
	{
		throw new MyException("自定义异常MyException");
	}
}
