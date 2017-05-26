package com.haozi.spring.simple.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListController
{

	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp)
	{
		Map map = new HashMap();
		map.put("cityList", Arrays.asList("山东", "北京", "上海"));
		return new ModelAndView("list", map);
	}
}
