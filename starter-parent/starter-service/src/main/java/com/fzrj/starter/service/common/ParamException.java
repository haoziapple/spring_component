package com.fzrj.starter.service.common;

/**
 * @className:com.fzrj.starter.service.common.ParamException
 * @description:自定义参数非法异常
 * @version:v1.0.0
 * @date:2017年5月18日 下午1:06:10
 * @author:WangHao
 */
public class ParamException extends IllegalArgumentException
{
	private static final long serialVersionUID = 1L;

	public ParamException(String message)
	{
		super(message);
	}
}
