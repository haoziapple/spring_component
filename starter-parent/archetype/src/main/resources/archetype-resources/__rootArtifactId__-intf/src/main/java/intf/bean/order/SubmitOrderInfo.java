#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.intf.bean.order;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import ${package}.intf.bean.common.ReqBean;
import ${package}.intf.bean.goods.GoodsInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @className:${package}.intf.bean.order.SubmitOrderInfo
 * @description:提交订单请求bean
 * @version:v1.0.0
 * @date:2017年5月17日 下午1:32:12
 * @author:WangHao
 */

@ApiModel("提交订单请求bean")
public class SubmitOrderInfo extends ReqBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "用户id为空")
	@ApiModelProperty("用户id")
	private String userId;
	@ApiModelProperty("订单总价")
	private String totalPrice;
	@ApiModelProperty("商品列表")
	private List<GoodsInfo> goodsList;

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getTotalPrice()
	{
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice)
	{
		this.totalPrice = totalPrice;
	}

	public List<GoodsInfo> getGoodsList()
	{
		return goodsList;
	}

	public void setGoodsList(List<GoodsInfo> goodsList)
	{
		this.goodsList = goodsList;
	}

	@Override
	public String toString()
	{
		return "SubmitOrderInfo [userId=" + userId + ", totalPrice=" + totalPrice + ", goodsList=" + goodsList
				+ ", toString()=" + super.toString() + "]";
	}
}
