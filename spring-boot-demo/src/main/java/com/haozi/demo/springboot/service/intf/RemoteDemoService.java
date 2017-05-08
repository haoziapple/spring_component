package com.haozi.demo.springboot.service.intf;

import com.haozi.demo.springboot.bean.po.order.OrderBean;

/**
 * @className:com.haozi.demo.springboot.service.intf.RemoteDemoService
 * @description:远程调用demo用Serivce
 * @version:v1.0.0
 * @date:2017年3月10日 上午9:48:38
 * @author:WangHao
 */
public interface RemoteDemoService
{
	OrderBean remoteQueryOrder(String orderCode);
}
