package com.haozi.demo.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.haozi.demo.springboot.bean.po.order.OrderBean;
import com.haozi.demo.springboot.dao.order.InsertOrderDAO;
import com.haozi.demo.springboot.dao.order.QueryOrderDAO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "订单相关api")
@RestController
@RequestMapping("/order")
public class OrderController
{
	private final static Logger logger = LoggerFactory.getLogger(OrderController.class);
	@Autowired
	private QueryOrderDAO queryOrderDAO;
	@Autowired
	private InsertOrderDAO insertOrderDAO;

	/**
	 * @Description:查询订单信息
	 * @param orderCode
	 * @return
	 * @version:v1.0
	 * @author:WangHao
	 * @date:2017年3月9日 上午11:18:50
	 */
	@ApiOperation(value = "查询订单信息", notes = "根据订单号查询订单详情")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "body", name = "orderCode", dataType = "String", required = true, value = "订单编号") })
	@ApiResponses({ @ApiResponse(code = 400, message = "请求参数没填好"),
			@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对") })

	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public OrderBean query(@RequestBody String orderCode)
	{
		logger.debug("/order/query操作， orderCode:" + orderCode);
		OrderBean bean = queryOrderDAO.queryOrder(orderCode);
		return bean;
	}

	/**
	 * @Description:插入订单信息
	 * @param orderBean
	 * @return
	 * @version:v1.0
	 * @author:WangHao
	 * @date:2017年3月9日 上午11:19:06
	 */
	@ApiOperation(value = "插入订单信息", notes = "插入订单信息")

	@Transactional
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@RequestBody OrderBean orderBean)
	{
		logger.debug("/order/insert操作" + orderBean);
		int i = insertOrderDAO.insertOrder(orderBean);
		// throw new RuntimeException();
		
		int t = insertOrderDAO.insertOrder(new OrderBean());
		return Integer.toString(i);
	}
}
