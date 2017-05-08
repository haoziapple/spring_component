package com.haozi.demo.springboot.bean.po.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @className:com.haozi.demo.springboot.bean.po.order.OrderBean
 * @description:订单信息
 * @version:v1.0.0
 * @date:2017年3月8日 下午2:06:19
 * @author:WangHao
 */
@ApiModel(value = "订单信息bean")
public class OrderBean
{
	/**
	 * 订单编号
	 */
	@ApiModelProperty(value = "订单编号", required = true)
	private String orderCode = "";

	/**
	 * 订单状态(0待确认 1待支付 2支付中 3已支付/待发货 4已发货/待收货 5交易成功 6交易关闭)
	 */
	private String orderStatus = "";

	/**
	 * 订单金额
	 */
	private String orderMoney = "";

	/**
	 * 支付订单编号
	 */
	private String payOrderCode = "";

	/**
	 * 配送方式（0物流配送 1自提）
	 */
	private String deliveryType = "";

	/**
	 * 用户key
	 */
	private String userKey = "";

	/**
	 * 商家key
	 */
	private String shopKey = "";

	private String orderSource;

	public String getOrderCode()
	{
		return orderCode;
	}

	public void setOrderCode(String orderCode)
	{
		this.orderCode = orderCode;
	}

	public String getOrderStatus()
	{
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus)
	{
		this.orderStatus = orderStatus;
	}

	public String getOrderMoney()
	{
		return orderMoney;
	}

	public void setOrderMoney(String orderMoney)
	{
		this.orderMoney = orderMoney;
	}

	public String getPayOrderCode()
	{
		return payOrderCode;
	}

	public void setPayOrderCode(String payOrderCode)
	{
		this.payOrderCode = payOrderCode;
	}

	public String getDeliveryType()
	{
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType)
	{
		this.deliveryType = deliveryType;
	}

	public String getUserKey()
	{
		return userKey;
	}

	public void setUserKey(String userKey)
	{
		this.userKey = userKey;
	}

	public String getShopKey()
	{
		return shopKey;
	}

	public void setShopKey(String shopKey)
	{
		this.shopKey = shopKey;
	}

	public String getOrderSource()
	{
		return orderSource;
	}

	public void setOrderSource(String orderSource)
	{
		this.orderSource = orderSource;
	}

	@Override
	public String toString()
	{
		return "OrderBean [orderCode=" + orderCode + ", orderStatus=" + orderStatus + ", orderMoney=" + orderMoney
				+ ", payOrderCode=" + payOrderCode + ", deliveryType=" + deliveryType + ", userKey=" + userKey
				+ ", shopKey=" + shopKey + ", orderSource=" + orderSource + "]";
	}
}
