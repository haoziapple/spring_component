package com.haozi.springboot.modeluse.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.haozi.springboot.modeluse.bean.PersonBean;

@Controller
public class UseModelController
{
	private static final Logger logger = LoggerFactory.getLogger(UseModelController.class);

	@ModelAttribute
	public void populateModel(@RequestParam String abc, Model model)
	{
		logger.info("测试model赋值:{}", abc);
		model.addAttribute("attributeName", abc);
	}

	@ModelAttribute
	public PersonBean personModel()
	{
		PersonBean p = new PersonBean()
		{
			{
				setAge("11");
				setName("wanghao");
				setSex("male");
			}
		};
		logger.info("测试model对象赋值，默认对象名称personBean:{}", p);
		return p;
	}

	@ModelAttribute("p")
	public PersonBean modelWithValue()
	{
		PersonBean p = new PersonBean()
		{
			{
				setAge("22");
				setName("tt");
				setSex("female");
			}
		};
		logger.info("测试model对象赋值，指定对象名称p:{}", p);
		return p;
	}

	@RequestMapping(value = "/helloWorld")
	public String helloWorld()
	{
		logger.info("helloWorld");
		return "usemodel/test";
	}

	@RequestMapping("/person")
	public String person(PersonBean p)
	{
		p.setAge("22");
		logger.info("测试对象合并，默认对象名称personBean:{}", p);
		return "usemodel/test";
	}

	@RequestMapping("/personValue")
	public String personValue(@ModelAttribute("p") PersonBean p)
	{
		p.setAge("33");
		logger.info("测试对象合并，指定对象名称p:{}", p);
		return "usemodel/test";
	}

}
