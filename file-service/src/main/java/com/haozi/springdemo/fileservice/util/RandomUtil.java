package com.haozi.springdemo.fileservice.util;

import org.apache.commons.lang.RandomStringUtils;

/**
 * @className:com.haozi.springdemo.fileservice.util.RandomUtil
 * @description:随机工具类,使用RandomStringUtils
 * @version:v1.0.0
 * @date:2017年8月9日 下午6:40:12
 * @author:WangHao
 */
public class RandomUtil
{
	private static final String NUM = "0123456789";

	private static final String TEXT = "0123456789abcdefghijklmnopqrstuvwxyz";
	
	private RandomUtil()
	{
		super();
	}

	/**
	 * @Description:获取一定位数的随机字串-数字
	 * @param i
	 * @return
	 * @version:v1.0
	 * @author:WangHao
	 * @date:2017年8月9日 下午6:42:07
	 */
	public static String randomNum(int i)
	{
		return RandomStringUtils.random(i, NUM);
	}

	/**
	 * @Description:获取一定位数的随机字串-数字加小写字母
	 * @param i
	 * @return
	 * @version:v1.0
	 * @author:WangHao
	 * @date:2017年8月9日 下午6:43:44
	 */
	public static String randomText(int i)
	{
		return RandomStringUtils.random(i, TEXT);
	}
}
