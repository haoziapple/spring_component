package com.haozi.demo.springboot.dao.payorder;

import com.haozi.demo.springboot.bean.po.payorder.PayOrderBean;

/**
 * @className:com.haozi.demo.springboot.dao.payorder.PayOrderDAO
 * @description:TODO
 * @version:v1.0.0
 * @date:2017年3月8日 下午4:04:37
 * @author:WangHao
 */
public interface PayOrderDAO
{
	int insertPayOrder(PayOrderBean payOrderBean);

	PayOrderBean queryPayOrder(String payOrderCode);
}
