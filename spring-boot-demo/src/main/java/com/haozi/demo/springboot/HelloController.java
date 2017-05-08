package com.haozi.demo.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController
{
	// 定义一个全局的记录器，通过LoggerFactory获取
	private final static Logger logger = LoggerFactory.getLogger(HelloController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index()
	{
		logger.debug("Greetings from 王昊!");
		return "Greetings from 王昊!";
	}
}
