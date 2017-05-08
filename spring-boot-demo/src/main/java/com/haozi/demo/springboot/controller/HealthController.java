package com.haozi.demo.springboot.controller;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className:com.haozi.demo.springboot.controller.HealthController
 * @description:health服务Controller
 * @version:v1.0.0
 * @date:2017年3月8日 上午9:57:29
 * @author:WangHao
 */
@RestController
@RequestMapping("/health")
public class HealthController implements HealthIndicator
{

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@Override
	public Health health()
	{
//		return Health.up().build();
		return new Health.Builder().up().withDetail("version", "1.1.2").build();
	}

}
