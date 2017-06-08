package com.haozi.demo.sidemo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@SpringBootApplication
@IntegrationComponentScan
@Configuration
@EnableIntegration
public class Application
{
	public static void main(String[] args)
	{
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		System.out.println("Let's inspect the beans provided by Spring Boot:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames)
		{
			System.out.println(beanName);
		}
	}

	@Bean
	@Description("Entry to the messaging system through the gateway.")
	public MessageChannel requestChannel()
	{
		return new DirectChannel();
	}

	@Bean
	@Description("Sends request messages to the web service outbound gateway")
	public MessageChannel invocationChannel(@Qualifier("wsOutboundGateway") MessageHandler wsOutboundGateway)
	{
		DirectChannel channel = new DirectChannel();
		channel.subscribe(wsOutboundGateway);

		return channel;
	}

	@Bean
	@Description("Sends web service responses to both the client and a database")
	public MessageChannel responseChannel()
	{
		return new PublishSubscribeChannel();
	}

	@Bean
	@Description("Stores non filtered messages to the database")
	public MessageChannel storeChannel(@Qualifier("mongodbAdapter") MessageHandler mongoOutboundAdapter)
	{
		DirectChannel channel = new DirectChannel();
		channel.subscribe(mongoOutboundAdapter);

		return channel;
	}
}
