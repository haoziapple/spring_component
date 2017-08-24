package com.haozi.component.oastruct.service.common.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @className:com.haozi.component.oastruct.service.common.util.HttpContextUtil
 * @description:请求上下文工具类
 * @version:v1.0.0
 * @date:2017年8月15日 下午3:12:56
 * @author:WangHao
 */
public class HttpContextUtil
{
	public static HttpServletRequest getHttpServletRequest()
	{
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	private HttpContextUtil()
	{
		super();
	}
}
