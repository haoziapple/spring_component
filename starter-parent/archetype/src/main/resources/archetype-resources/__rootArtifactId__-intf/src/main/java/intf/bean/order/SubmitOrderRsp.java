#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.intf.bean.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("提交订单返回bean")
public class SubmitOrderRsp
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
