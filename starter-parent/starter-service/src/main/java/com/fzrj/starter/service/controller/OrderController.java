package com.fzrj.starter.service.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fzrj.starter.intf.OrderIntf;
import com.fzrj.starter.intf.bean.order.SubmitOrderInfo;
import com.fzrj.starter.intf.bean.order.SubmitOrderRsp;
import com.fzrj.starter.service.common.aop.AddLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("订单相关api")
@RestController
@RequestMapping("/order")
@CrossOrigin(value = { "http://10.108.26.101:8089" })
public class OrderController
{
	private final static Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderIntf orderIntf;

	@AddLog
	@ApiOperation(value = "提交订单", notes = "提交订单并返回订单编号")
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public SubmitOrderRsp submit(@RequestBody @Valid SubmitOrderInfo reqInfo, BindingResult bindingResult)
	{
		return orderIntf.submitOrder(reqInfo);
	}

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public SubmitOrderRsp test(SubmitOrderInfo reqInfo, BindingResult bindingResult, HttpServletRequest req)
	{
		logger.info(req.getRemoteHost());
		return new SubmitOrderRsp();
	}
}
