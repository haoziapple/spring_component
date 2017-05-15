package com.haozi.springboot.modeluse.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController
{
	@ResponseBody
	@RequestMapping("/hello")
	public String hello()
	{
		return "Hello World";
	}

	@RequestMapping("/")
	public String index(ModelMap map)
	{
		map.addAttribute("host", "thymeleaf-test-use-host");
		return "index";
	}

	@RequestMapping("/fm")
	public String fm(ModelMap map)
	{
		map.addAttribute("host", "freemaker-test-use-host");
		return "index_fm";
	}
	
	@RequestMapping("/vm")
	public String vm(ModelMap map)
	{
		map.addAttribute("host", "velocity-test-use-host");
		return "index_vm";
	}
}
