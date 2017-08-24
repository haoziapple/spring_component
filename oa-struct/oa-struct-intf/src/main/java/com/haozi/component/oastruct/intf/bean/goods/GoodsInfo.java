package com.haozi.component.oastruct.intf.bean.goods;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("商品信息bean")
public class GoodsInfo
{
	@ApiModelProperty("商品id")
	private String goodsId;
	@ApiModelProperty("商品名称")
	private String goodsName;
	@ApiModelProperty("商品单价")
	private String goodsPrice;
	@ApiModelProperty("商品数量")
	private String goodsNum;

	public String getGoodsName()
	{
		return goodsName;
	}

	public void setGoodsName(String goodsName)
	{
		this.goodsName = goodsName;
	}

	public String getGoodsPrice()
	{
		return goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice)
	{
		this.goodsPrice = goodsPrice;
	}

	public String getGoodsNum()
	{
		return goodsNum;
	}

	public void setGoodsNum(String goodsNum)
	{
		this.goodsNum = goodsNum;
	}

	public String getGoodsId()
	{
		return goodsId;
	}

	public void setGoodsId(String goodsId)
	{
		this.goodsId = goodsId;
	}

	@Override
	public String toString()
	{
		return "GoodsInfo [goodsId=" + goodsId + ", goodsName=" + goodsName + ", goodsPrice=" + goodsPrice
				+ ", goodsNum=" + goodsNum + "]";
	}
}
