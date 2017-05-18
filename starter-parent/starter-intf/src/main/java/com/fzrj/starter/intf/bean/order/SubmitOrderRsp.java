package com.fzrj.starter.intf.bean.order;

import com.fzrj.starter.intf.bean.common.RspBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("提交订单返回bean")
public class SubmitOrderRsp extends RspBean
{
	@ApiModelProperty("订单id")
	private String orderId;

	public String getOrderId()
	{
		return orderId;
	}

	public void setOrderId(String orderId)
	{
		this.orderId = orderId;
	}

	@Override
	public String toString()
	{
		return "SubmitOrderRsp [orderId=" + orderId + ", toString()=" + super.toString() + "]";
	}

}
