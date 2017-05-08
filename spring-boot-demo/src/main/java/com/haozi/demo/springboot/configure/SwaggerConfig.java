package com.haozi.demo.springboot.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
// 访问地址：swagger-ui.html
public class SwaggerConfig
{
	public static final String SWAGGER_SCAN_BASE_PACKAGE = "com.haozi.demo.springboot.controller";
	public static final String VERSION = "1.0.0";
	public static final String API_DESC = "测试用接口说明";
	public static final String EMAIL = "haozixiaowang@163.com";

	@Bean
	public ApiInfo apiInfo()
	{
		return new ApiInfoBuilder().title("Swagger API").description(API_DESC).license("Apache 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").version(VERSION).contact(EMAIL).build();
	}

	@Bean
	public Docket customImplementation()
	{
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.any()
//						.basePackage(SWAGGER_SCAN_BASE_PACKAGE)
						).build().apiInfo(apiInfo());
	}
}
