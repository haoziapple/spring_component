package com.haozi.springdemo.fileservice;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.catalina.servlet4preview.http.HttpServletRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import com.haozi.springdemo.fileservice.config.PathConfig;

@SpringBootApplication
@EnableAsync
public class Application extends SpringBootServletInitializer
{
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	// jar包方式启动
	public static void main(String[] args)
	{
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames)
		{
			log.info("loaded bean's name: {}", beanName);
		}

		PathConfig test = ctx.getBean(PathConfig.class);
		if (log.isInfoEnabled())
			log.info("load upload path map: {}", test.getMap().toString());
	}

	// war包方式启动
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
	{
		return builder.sources(this.getClass());
	}

	/**
	 * 添加过滤器
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean()
	{
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new Filter()
		{
			@Override
			public void init(FilterConfig filterConfig) throws ServletException
			{
				log.info("filter initialize");
			}

			@Override
			public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
					FilterChain filterChain) throws IOException, ServletException
			{
				log.info(servletRequest.getRemoteHost() + " enter the api filter");
				filterChain.doFilter(new HttpServletRequestWrapper((HttpServletRequest) servletRequest)
				{

				}, new HttpServletResponseWrapper((HttpServletResponse) servletResponse)
				{

				});
			}

			@Override
			public void destroy()
			{
				log.info("filter destroy");
			}
		});
		registrationBean.addUrlPatterns("/api/*");
		registrationBean.addInitParameter("passIP", "0:0:0:0:0:0:0:1|127.0.0.1");
		registrationBean.setName("myFilter");
		registrationBean.setOrder(1);

		return registrationBean;
	}
}
