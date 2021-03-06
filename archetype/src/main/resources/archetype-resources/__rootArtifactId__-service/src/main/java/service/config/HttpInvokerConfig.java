#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import com.aquatic.schedule.service.remote.JobService;
import ${package}.intf.OrderIntf;

/**
 * @className:${package}.service.config.HttpInvokerConfig
 * @description:HttpInvoker配置bean
 * @version:v${version}
 * @date:2017年5月18日 下午1:21:17
 * @author:WangHao
 */
@Configuration
public class HttpInvokerConfig
{
	@Value("${symbol_dollar}{remote.jobservice.url}")
	private String jobServiceUrl;

	@Autowired
	private OrderIntf orderIntf;

	// 暴露的订单服务
	@Bean(name = "/orderService")
	public HttpInvokerServiceExporter exportOrderService()
	{
		HttpInvokerServiceExporter expoter = new HttpInvokerServiceExporter();
		expoter.setService(orderIntf);
		expoter.setServiceInterface(OrderIntf.class);
		return expoter;
	}

	// 调用远程定时器服务
	@Bean
	public HttpInvokerProxyFactoryBean jobService()
	{
		HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
		bean.setServiceUrl(jobServiceUrl + "/jobService");
		bean.setServiceInterface(JobService.class);
		return bean;
	}
}
