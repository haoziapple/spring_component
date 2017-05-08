package com.haozi.demo.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.haozi.demo.springboot.bean.po.payorder.PayOrderBean;
import com.haozi.demo.springboot.dao.payorder.PayOrderDAO;

@RestController
@RequestMapping("/payorder")
public class PayOrderController
{
	private final static Logger logger = LoggerFactory.getLogger(PayOrderController.class);
	@Autowired
	private PayOrderDAO payOrderDAO;

	@Transactional
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@RequestBody PayOrderBean payOrderBean)
	{
		logger.debug("/payorder/insert操作" + payOrderBean);
		int i = payOrderDAO.insertPayOrder(payOrderBean);
		logger.debug("/payorder/insert操作结果" + payOrderBean);
		return Integer.toString(i);
	}

	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public PayOrderBean query(@RequestBody String payOrderCode)
	{
		return payOrderDAO.queryPayOrder(payOrderCode);
	}
}
