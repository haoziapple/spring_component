package com.haozi.spring.simple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/my")
public class MyController
{
	@RequestMapping(value = "/getString")
	public @ResponseBody String getString()
	{
		System.out.println("getString");
		return "<html><body><h2>Hello World!</h2></body></html>";
	}
}
