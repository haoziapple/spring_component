package it.pkg.intf;

import it.pkg.intf.bean.order.SubmitOrderInfo;
import it.pkg.intf.bean.order.SubmitOrderRsp;

/**
 * @className:it.pkg.intf.OrderIntf
 * @description:订单接口
 * @version:v0.1-SNAPSHOT
 * @date:2017年5月17日 下午1:04:49
 * @author:WangHao
 */
public interface OrderIntf
{
	// 提交订单
	SubmitOrderRsp submitOrder(SubmitOrderInfo submitOrderInfo);
}
