package com.haozi.component.oastruct.service.config;

import com.haozi.component.oastruct.intf.OATreeService;
import com.haozi.component.oastruct.service.common.util.tree.TreeStructUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import com.aquatic.schedule.service.remote.JobService;

/**
 * @className:com.haozi.component.oastruct.service.config.HttpInvokerConfig
 * @description:HttpInvoker配置bean
 * @version:v1.0.0
 * @date:2017年5月18日 下午1:21:17
 * @author:WangHao
 */
@Configuration
public class HttpInvokerConfig {
    @Value("${remote.jobservice.url}")
    private String jobServiceUrl;

    @Autowired
    private OATreeService oaTreeService;

    // 调用远程定时器服务
    @Bean
    public HttpInvokerProxyFactoryBean jobService() {
        HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
        bean.setServiceUrl(jobServiceUrl + "/jobService");
        bean.setServiceInterface(JobService.class);
        return bean;
    }

    // 暴露oa树结构服务
    @Bean(name = "/oaTreeService")
    public HttpInvokerServiceExporter oaTreeServiceExporter() {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(oaTreeService);
        exporter.setServiceInterface(OATreeService.class);
        return exporter;
    }

    @Bean("oaService")
    public HttpInvokerProxyFactoryBean oaService() {
        HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
        bean.setServiceUrl("http://10.108.26.232:8086/oa-struct-service/oaTreeService");
        bean.setServiceInterface(OATreeService.class);
        return bean;
    }

}
