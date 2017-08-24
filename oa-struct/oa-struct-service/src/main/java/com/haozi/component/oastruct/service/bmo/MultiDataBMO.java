package com.haozi.component.oastruct.service.bmo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haozi.component.oastruct.service.dao.Data1Mapper;
import com.haozi.component.oastruct.service.dao.Data2Mapper;
import com.haozi.component.oastruct.service.dao.Data3Mapper;

/**
 * @className:com.haozi.component.oastruct.service.bmo.MultiDataBMO
 * @description:多数据源测试bmo,由于在service层切换数据源，事务只能加在bmo层,仅作为示例使用，可以删除
 * @version:v1.0.0
 * @date:2017年8月14日 下午6:41:56
 * @author:WangHao
 */
@Service
public class MultiDataBMO
{
	@Autowired
	private Data1Mapper data1;

	@Autowired
	private Data2Mapper data2;

	@Autowired
	private Data3Mapper data3;

	@Transactional
	public int get1()
	{
		return data1.get();
	}

	@Transactional
	public int get2()
	{
		return data2.get();
	}

	@Transactional
	public int get3()
	{
		return data3.get();
	}
}
