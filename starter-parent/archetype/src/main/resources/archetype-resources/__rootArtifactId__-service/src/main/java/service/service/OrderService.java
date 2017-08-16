#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${package}.intf.OrderIntf;
import ${package}.intf.bean.order.SubmitOrderInfo;
import ${package}.intf.bean.order.SubmitOrderRsp;
import ${package}.service.dao.AquaticOrderUserInfoMapper;
import ${package}.service.po.AquaticOrderUserInfo;

/**
 * @className:${package}.service.service.OrderService
 * @description:订单接口实现类
 * @version:v1.0.0
 * @date:2017年5月17日 下午4:59:02
 * @author:WangHao
 */
@Service
public class OrderService implements OrderIntf
{
	private final static Logger logger = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private AquaticOrderUserInfoMapper aquaticOrderUserInfoMapper;

	@Transactional
	@Override
	public SubmitOrderRsp submitOrder(SubmitOrderInfo submitOrderInfo)
	{
		AquaticOrderUserInfo record = new AquaticOrderUserInfo();
		record.setOrderCode(UUID.randomUUID().toString());
		record.setUserKey("userKey");
		record.setShopKey("shopKey");
		record.setOrderMoney(11L);
		record.setOrderStatus("0");
		record.setOrderSource("0");
		record.setDeliveryType("0");
		aquaticOrderUserInfoMapper.insertSelective(record);

		SubmitOrderRsp rsp = new SubmitOrderRsp();
		BeanUtils.copyProperties(record, rsp);
		rsp.setOrderId(record.getOrderCode());
		return rsp;
	}

}
