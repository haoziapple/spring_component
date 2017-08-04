package com.haozi.springdemo.fileservice;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.haozi.springdemo.fileservice.config.PathConfig;

@SpringBootApplication
public class Application
{
	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args)
	{
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames)
		{
			logger.info("loaded bean's name: {}", beanName);
		}

		PathConfig test = ctx.getBean(PathConfig.class);
		System.out.println(test.getMap());
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
			private String PASSHOSTS;

			@Override
			public void init(FilterConfig filterConfig) throws ServletException
			{
				this.PASSHOSTS = filterConfig.getInitParameter("passIP");
			}

			@Override
			public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
					FilterChain filterChain) throws IOException, ServletException
			{

			}

			@Override
			public void destroy()
			{

			}
		});
		registrationBean.addUrlPatterns("/api/*");
		registrationBean.addInitParameter("passIP", "0:0:0:0:0:0:0:1|127.0.0.1");
		registrationBean.setName("myFilter");
		registrationBean.setOrder(1);

		return registrationBean;
	}
}
