package com.haozi.demo.springboot.service;

import org.springframework.stereotype.Service;

import com.haozi.demo.springboot.bean.po.order.OrderBean;
import com.haozi.demo.springboot.service.intf.RemoteDemoService;

@Service
//@Service("/remoteService")
public class RemoteDemoServiceImpl implements RemoteDemoService
{

	@Override
	public OrderBean remoteQueryOrder(String orderCode)
	{
		return null;
	}

}
