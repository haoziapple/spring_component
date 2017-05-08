package com.haozi.demo.springboot.dao.order;

import com.haozi.demo.springboot.bean.po.order.OrderBean;

/**
 * @className:com.haozi.demo.springboot.dao.order.InsertOrderDAO
 * @description:TODO
 * @version:v1.0.0
 * @date:2017年3月8日 下午3:08:51
 * @author:WangHao
 */
public interface InsertOrderDAO
{
	int insertOrder(OrderBean orderBean);
}
