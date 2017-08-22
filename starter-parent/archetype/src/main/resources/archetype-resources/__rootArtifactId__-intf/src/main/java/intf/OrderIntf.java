#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.intf;

import ${package}.intf.bean.common.Page;
import ${package}.intf.bean.order.SubmitOrderInfo;
import ${package}.intf.bean.order.SubmitOrderRsp;

/**
 * @className:${package}.intf.OrderIntf
 * @description:订单接口
 * @version:v1.0.0
 * @date:2017年5月17日 下午1:04:49
 * @author:WangHao
 */
public interface OrderIntf
{
	// 提交订单
	SubmitOrderRsp submitOrder(SubmitOrderInfo submitOrderInfo);
	
	/**
	 * @Description:查询某个用户的所有订单--分页使用示例
	 * @param userId
	 * @return
	 * @version:v1.0
	 * @author:WangHao
	 * @date:2017年8月22日 上午11:08:02
	 */
	Page<SubmitOrderRsp> queryOrder(String userId, int pageNo, int pageSize);
}
