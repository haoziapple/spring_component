#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.intf;

import ${package}.intf.bean.order.SubmitOrderInfo;
import ${package}.intf.bean.order.SubmitOrderRsp;

/**
 * @className:${package}.intf.OrderIntf
 * @description:订单接口
 * @version:v${version}
 * @date:2017年5月17日 下午1:04:49
 * @author:WangHao
 */
public interface OrderIntf
{
	// 提交订单
	SubmitOrderRsp submitOrder(SubmitOrderInfo submitOrderInfo);
}
