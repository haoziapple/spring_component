#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ${package}.intf.OrderIntf;
import ${package}.intf.bean.order.SubmitOrderInfo;
import ${package}.intf.bean.order.SubmitOrderRsp;
import ${package}.service.common.aop.AddLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("订单相关api")
@RestController
@RequestMapping("/order")
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
}
