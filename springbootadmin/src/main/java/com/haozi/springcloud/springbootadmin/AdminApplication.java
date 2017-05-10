package com.haozi.springcloud.springbootadmin;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import de.codecentric.boot.admin.config.EnableAdminServer;

/**
 * @className:com.haozi.springcloud.springbootadmin.AdminApplication
 * @description:springboot管理台
 * @version:v1.0.0
 * @date:2017年5月10日 上午9:42:23
 * @author:WangHao
 */
@EnableAdminServer
@SpringBootApplication
public class AdminApplication
{
	public static void main(String[] args)
	{
		ApplicationContext ctx = SpringApplication.run(AdminApplication.class, args);

		System.out.println("Let's inspect the beans provided by Spring Boot:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames)
		{
			System.out.println(beanName);
		}
	}
}
