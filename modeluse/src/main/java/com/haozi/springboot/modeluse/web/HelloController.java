package com.haozi.springboot.modeluse.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.haozi.springboot.modeluse.bean.PersonBean;

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
		List<PersonBean> list = new ArrayList();
		list.add(new PersonBean()
		{
			{
				setAge("11");
				setName("wanghao");
				setSex("boy");
			}
		});
		list.add(new PersonBean()
		{
			{
				setAge("22");
				setName("zhaoyue");
				setSex("girl");
			}
		});

		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("k1", "1");
		mapData.put("k2", "2");
		mapData.put("k3", 3);

		map.addAttribute("list", list);
		map.addAttribute("host", "freemaker-test-use-host");
		map.addAttribute("data", mapData);
		map.addAttribute("name", "Someone & Co<>/+&gt;&lt;");
		return "index_fm";
	}

	@RequestMapping("/vm")
	public String vm(ModelMap map)
	{
		map.addAttribute("host", "velocity-test-use-host");
		return "index_vm";
	}
}
