package com.haozi.springdemo.fileservice.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class MultipartConfig
{
	@Autowired
	Environment env;

	private static final String MB_SUFFIX = "MB";

	@Bean
	public MultipartConfigElement multipartConfigElement()
	{
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(env.getProperty("file.maxSize") + MB_SUFFIX);
		return factory.createMultipartConfig();
	}
}
