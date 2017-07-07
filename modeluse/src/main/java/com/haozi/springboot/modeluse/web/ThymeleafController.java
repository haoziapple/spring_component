package com.haozi.springboot.modeluse.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/th")
public class ThymeleafController
{
	@RequestMapping("/")
	public String index(ModelMap map)
	{
		map.addAttribute("host", "thymeleaf-test-use-host");
		return "th/th";
	}
}
