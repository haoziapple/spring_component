#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.service.data3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${package}.intf.bean.common.ReqBean;
import ${package}.service.bmo.MultiDataBMO;

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
