package com.haozi.demo.springboot.dao.order;

import com.haozi.demo.springboot.bean.po.order.OrderBean;

/**
 * @className:com.haozi.demo.springboot.dao.order.QueryOrderDAO
 * @description:订单查询DAO
 * @version:v1.0.0
 * @date:2017年3月8日 下午2:18:56
 * @author:WangHao
 */
public interface QueryOrderDAO
{

	/**
	 * @Description:远程调用查询订单
	 * @param orderCode
	 * @return
	 * @version:v1.0
	 * @author:WangHao
	 * @date:2017年3月10日 上午9:54:43
	 */
	OrderBean queryOrder(String orderCode);
}
