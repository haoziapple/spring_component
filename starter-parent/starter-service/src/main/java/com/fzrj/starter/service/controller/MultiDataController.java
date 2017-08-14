package com.fzrj.starter.service.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fzrj.starter.intf.bean.order.SubmitOrderInfo;
import com.fzrj.starter.intf.bean.order.SubmitOrderRsp;
import com.fzrj.starter.service.service.data1.Data1Service;
import com.fzrj.starter.service.service.data2.Data2Service;
import com.fzrj.starter.service.service.data3.Data3Service;

import io.swagger.annotations.Api;

/**
 * @className:com.fzrj.starter.service.controller.MultiDataController
 * @description:多数据源访问Controller，仅作为示例使用，可删除
 * @version:v1.0.0
 * @date:2017年8月14日 下午6:03:35
 * @author:WangHao
 */
@Api("多数据源访问api")
@RestController
@RequestMapping("/data")
public class MultiDataController
{
	private static final Logger logger = LoggerFactory.getLogger(MultiDataController.class);

	@Autowired
	private Data1Service data1Service;

	@Autowired
	private Data2Service data2Service;

	@Autowired
	private Data3Service data3Service;

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public SubmitOrderRsp test(SubmitOrderInfo reqInfo, BindingResult bindingResult, HttpServletRequest req)
	{
		logger.info(req.getRemoteHost());
		int i = data1Service.get();
		logger.info("data1 get {}", i);

		i = data2Service.get();
		logger.info("data2 get {}", i);

		i = data3Service.get();
		logger.info("data3 get {}", i);
		return new SubmitOrderRsp();
	}
}
