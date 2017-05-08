package com.haozi.demo.springboot.bean.po.payorder;

public class PayOrderBean
{
	private int id;
	
	/**
	 * 订单编号
	 */
	private String orderCode = "";

	/**
	 * 订单状态
	 */
	private String baseOrderStatus = "";

	/**
	 * 支付订单编号
	 */
	private String payOrderCode = "";

	/**
	 * 支付订单状态
	 */
	private String orderStatus = "";

	/**
	 * 查询交易流水号
	 */
	private String tradeQueryCode = "";

	/**
	 * 添加时间
	 */
	private String addTime = "";

	/**
	 * 支付时间
	 */
	private String payTime = "";

	/**
	 * 支付方式（1银联 2支付宝 3微信）
	 */
	private String payWay = "";

	/**
	 * 支付类型(1手机支付 2wap支付 3pc端支付)
	 */
	private String payType = "";

	public String getPayOrderCode()
	{
		return payOrderCode;
	}

	public void setPayOrderCode(String payOrderCode)
	{
		this.payOrderCode = payOrderCode;
	}

	public String getOrderStatus()
	{
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus)
	{
		this.orderStatus = orderStatus;
	}

	public String getTradeQueryCode()
	{
		return tradeQueryCode;
	}

	public void setTradeQueryCode(String tradeQueryCode)
	{
		this.tradeQueryCode = tradeQueryCode;
	}

	public String getAddTime()
	{
		return addTime;
	}

	public void setAddTime(String addTime)
	{
		this.addTime = addTime;
	}

	public String getOrderCode()
	{
		return orderCode;
	}

	public void setOrderCode(String orderCode)
	{
		this.orderCode = orderCode;
	}

	public String getPayTime()
	{
		return payTime;
	}

	public void setPayTime(String payTime)
	{
		this.payTime = payTime;
	}

	public String getPayWay()
	{
		return payWay;
	}

	public void setPayWay(String payWay)
	{
		this.payWay = payWay;
	}

	public String getPayType()
	{
		return payType;
	}

	public void setPayType(String payType)
	{
		this.payType = payType;
	}

	public String getBaseOrderStatus()
	{
		return baseOrderStatus;
	}

	public void setBaseOrderStatus(String baseOrderStatus)
	{
		this.baseOrderStatus = baseOrderStatus;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@Override
	public String toString()
	{
		return "PayOrderBean [id=" + id + ", orderCode=" + orderCode + ", baseOrderStatus=" + baseOrderStatus
				+ ", payOrderCode=" + payOrderCode + ", orderStatus=" + orderStatus + ", tradeQueryCode="
				+ tradeQueryCode + ", addTime=" + addTime + ", payTime=" + payTime + ", payWay=" + payWay + ", payType="
				+ payType + "]";
	}
}
