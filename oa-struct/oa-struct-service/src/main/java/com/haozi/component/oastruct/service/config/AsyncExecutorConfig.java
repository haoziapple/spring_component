package com.haozi.component.oastruct.service.config;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @className:com.haozi.component.oastruct.service.config.AsyncExecutorConfig
 * @description:异步方法执行器配置
 * @version:v1.0.0
 * @date:2017年8月14日 上午11:08:32
 * @author:WangHao
 */
@Configuration
@EnableAsync
public class AsyncExecutorConfig implements AsyncConfigurer
{

	@Override
	public Executor getAsyncExecutor()
	{
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(10);
		executor.setQueueCapacity(25);
		executor.initialize();
		return executor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler()
	{
		return new SimpleAsyncUncaughtExceptionHandler();
	}

}
