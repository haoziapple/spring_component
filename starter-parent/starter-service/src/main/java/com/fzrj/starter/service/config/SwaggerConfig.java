package com.fzrj.starter.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @className:com.fzrj.starter.service.config.SwaggerConfig
 * @description:swagger配置bean
 * @version:v1.0.0
 * @date:2017年5月18日 下午1:23:13
 * @author:WangHao
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig
{
	@Value("${swagger.scan.base.package}")
	private String SWAGGER_SCAN_BASE_PACKAGE;
	@Value("${swagger.api.version}")
	private String VERSION;
	@Value("${spring.application.name}")
	private String TITLE;
	@Value("${swagger.api.desc}")
	private String API_DESC;
	@Value("${swagger.api.contact}")
	private String CONTACT;

	@Bean
	public ApiInfo apiInfo()
	{
		return new ApiInfoBuilder().title(TITLE).description(API_DESC).version(VERSION).contact(CONTACT).build();
	}

	@Bean
	public Docket customImplementation()
	{
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors
						// .any()
						.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
				.build().apiInfo(apiInfo());
	}
}
