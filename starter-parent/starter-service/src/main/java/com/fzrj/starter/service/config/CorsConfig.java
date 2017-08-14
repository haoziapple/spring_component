package com.fzrj.starter.service.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @className:com.fzrj.starter.service.config.CorsConfig
 * @description:全局的跨域访问配置类
 * @version:v1.0.0
 * @date:2017年8月14日 下午1:34:20
 * @author:WangHao
 */
@Component
@Configuration
@ConfigurationProperties(prefix = "cors.hosts")
public class CorsConfig
{
	private List<String> allow;
	
	private String allowMapping;

	public List<String> getAllow()
	{
		return allow;
	}

	public void setAllow(List<String> allow)
	{
		this.allow = allow;
	}

	public String getAllowMapping()
	{
		return allowMapping;
	}

	public void setAllowMapping(String allowMapping)
	{
		this.allowMapping = allowMapping;
	}

	@Bean
	public WebMvcConfigurer corsConfigurer()
	{
		return new WebMvcConfigurerAdapter()
		{
			@Override
			public void addCorsMappings(CorsRegistry registry)
			{
				registry.addMapping(allowMapping)
					.allowedOrigins(allow.toArray(new String[0]))
					.allowedMethods("GET", "POST")
					.allowedHeaders("*")
					.allowCredentials(true).maxAge(1800);
			}
		};
	}
}
