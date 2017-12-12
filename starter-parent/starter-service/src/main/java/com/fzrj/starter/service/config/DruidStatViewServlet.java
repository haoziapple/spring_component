package com.fzrj.starter.service.config;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.alibaba.druid.support.http.StatViewServlet;
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/druid/*", initParams = { 
//		@WebInitParam(name = "allow", value = "10.108.26.61,127.0.0.1"), // IP白名单
		@WebInitParam(name = "deny", value = "192.168.16.111"), // IP黑名单
		@WebInitParam(name = "loginUsername", value = "starter"), // 用户名
		@WebInitParam(name = "loginPassword", value = "starter"), // 密码
		@WebInitParam(name = "resetEnable", value = "false")// 禁用HTML页面上的“Reset
															// All”功能
})
public class DruidStatViewServlet extends StatViewServlet
{

}
