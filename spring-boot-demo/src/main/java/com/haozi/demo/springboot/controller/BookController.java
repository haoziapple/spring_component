package com.haozi.demo.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.haozi.demo.springboot.bean.BookBean;

@RestController
@RequestMapping("/book")
public class BookController
{
	private final static Logger logger = LoggerFactory.getLogger(BookController.class);
	@Autowired
	private BookBean bookBean;

	@RequestMapping(value = "/getBean", method = RequestMethod.GET)
	public String book()
	{
		logger.debug("测试properties配置" + bookBean);
		String s = "Hello Spring Boot! The BookName is " + bookBean.getName() + ";and Book Author is "
				+ bookBean.getAuthor() + ";and Book price is " + bookBean.getPrice();

		return s;
	}
}
