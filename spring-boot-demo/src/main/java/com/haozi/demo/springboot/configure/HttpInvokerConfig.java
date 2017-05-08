package com.haozi.demo.springboot.configure;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpComponentsHttpInvokerRequestExecutor;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import com.aquatic.schedule.service.remote.JobService;
import com.haozi.demo.springboot.service.RemoteDemoServiceImpl;
import com.haozi.demo.springboot.service.intf.RemoteDemoService;

/**
 * @className:com.haozi.demo.springboot.configure.HttpInvokerConfig
 * @description:http远程调用服务端配置
 * @version:v1.0.0
 * @date:2017年3月10日 上午10:42:24
 * @author:WangHao
 */
@Configuration
public class HttpInvokerConfig
{
	private String jobServiceUrl = "http://10.108.26.11:8080/aquatic_schedule_service/jobService";

	@Autowired
	private RemoteDemoServiceImpl remoteDemoServiceImpl;

	@Bean(name = "/remoteDemoService")
	public HttpInvokerServiceExporter exportRemoteDemoService()
	{
		HttpInvokerServiceExporter expoter = new HttpInvokerServiceExporter();
		expoter.setService(remoteDemoServiceImpl);
		expoter.setServiceInterface(RemoteDemoService.class);
		return expoter;
	}

	@Bean
	public HttpInvokerProxyFactoryBean jobService()
	{
		HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
		bean.setServiceUrl(jobServiceUrl);
		bean.setServiceInterface(JobService.class);
		return bean;
	}
}
