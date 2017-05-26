package com.haozi.spring.simple.controller;

/**
 * @className:com.haozi.spring.simple.controller.Bean
 * @description:TODO
 * @version:v1.0.0 
 * @date:2017年5月26日 下午4:47:16
 * @author:WangHao
 */
public class Bean
{
	private String a;

	private String b;

	public String getA()
	{
		return a;
	}

	public void setA(String a)
	{
		this.a = a;
	}

	public String getB()
	{
		return b;
	}

	public void setB(String b)
	{
		this.b = b;
	}

	@Override
	public String toString()
	{
		return "Bean [a=" + a + ", b=" + b + "]";
	}
}
