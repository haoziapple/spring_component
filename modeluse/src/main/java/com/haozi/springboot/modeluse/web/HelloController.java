package com.haozi.springboot.modeluse.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController
{
	@RequestMapping("/hello")
	public String hello()
	{
		return "hello";
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
