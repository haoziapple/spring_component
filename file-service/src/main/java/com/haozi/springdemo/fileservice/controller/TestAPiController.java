package com.haozi.springdemo.fileservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class TestAPiController
{
	private static final Logger logger = LoggerFactory.getLogger(TestAPiController.class);

	@RequestMapping(path="/test", method=RequestMethod.GET)
	@ResponseBody
	public String test()
	{
		logger.debug("test");
		return "success";
	}
}
