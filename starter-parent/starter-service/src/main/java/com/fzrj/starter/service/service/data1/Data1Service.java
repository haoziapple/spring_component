package com.fzrj.starter.service.service.data1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fzrj.starter.service.bmo.MultiDataBMO;

/**
 * @className:com.fzrj.starter.service.service.data1.Data1Service
 * @description:data1测试service，由于在service层切换数据源，事务必须加在bmo层
 * @version:v1.0.0
 * @date:2017年8月14日 下午6:22:39
 * @author:WangHao
 */
@Service
public class Data1Service
{
	private static final Logger logger = LoggerFactory.getLogger(Data1Service.class);

	@Autowired
	private MultiDataBMO multiDataBMO;

	public int get()
	{
		logger.info("data1Service");
		return multiDataBMO.get1();
	}
}
