package com.haozi.springdemo.fileservice.config;

import java.nio.charset.Charset;

import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig
{
	/**
	 * tomcat部署项目中包含中文名称文件，报404找不到文件错误
	 * <Connector port=”8080″ protocol=”HTTP/1.1″ connectionTimeout=”20000″
	 * redirectPort=”8443″ URIEncoding=”utf-8″ useBodyEncodingForURI=”true”/>
	 */
	@Bean
	public EmbeddedServletContainerFactory servletContainer()
	{
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
		tomcat.setUriEncoding(Charset.forName("UTF-8"));
		return tomcat;
	}
}
