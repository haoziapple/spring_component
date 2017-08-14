package com.fzrj.starter.service.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aquatic.schedule.service.remote.JobService;
import com.fzrj.starter.service.common.aop.AddLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @className:com.fzrj.starter.service.controller.JobController
 * @description:定时任务Controller，作为远程调用的示例，可删除
 * @version:v1.0.0
 * @date:2017年8月14日 下午6:02:07
 * @author:WangHao
 */
@Api("定时任务api")
@RestController
@RequestMapping("/job")
public class JobController
{
	private static final Logger logger = LoggerFactory.getLogger(JobController.class);

	@Autowired
	private JobService jobService;

	@AddLog
	@ApiOperation(value = "测试定时任务", notes = "测试新建一个固定的cron任务")
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public String test(@RequestBody String id)
	{
		jobService.addCronJob("jobName", "groupName", "triggerName", new HashMap<String, String>(),
				"0 16/16 * * * ? *");
		return "success";
	}
}
