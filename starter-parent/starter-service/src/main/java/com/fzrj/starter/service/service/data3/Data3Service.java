package com.fzrj.starter.service.service.data3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fzrj.starter.intf.bean.common.ReqBean;
import com.fzrj.starter.service.bmo.MultiDataBMO;

@Service
public class Data3Service
{
	private static final Logger logger = LoggerFactory.getLogger(Data3Service.class);

	@Autowired
	private MultiDataBMO multiDataBMO;

	public int get(ReqBean req)
	{
		logger.info("data3Service, req: {}", req);
		return multiDataBMO.get3();
	}
}
