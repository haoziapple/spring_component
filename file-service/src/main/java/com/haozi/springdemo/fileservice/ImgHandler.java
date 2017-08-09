package com.haozi.springdemo.fileservice;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.haozi.springdemo.fileservice.util.ThumbnailatorUtil;

@Component
public class ImgHandler
{
	// 图片最大尺寸
	@Value("${file.img.size.max}")
	private Integer MAX_SIZE;

	// 大图尺寸
	@Value("${file.img.size.b}")
	private Integer B_SIZE;

	// 中图尺寸
	@Value("${file.img.size.m}")
	private Integer M_SIZE;

	// 小图尺寸
	@Value("${file.img.size.s}")
	private Integer S_SIZE;

	private static final String B_END = "_b";

	private static final String M_END = "_m";

	private static final String S_END = "_s";

	private static final Logger logger = LoggerFactory.getLogger(ImgHandler.class);

	/**
	 * @Description:根据全路径处理图片，在同一路径下生成大、中、小三种规格图片
	 * @param fullPath
	 * @version:v1.0
	 * @author:WangHao
	 * @date:2017年8月9日 下午7:17:35
	 */
	public void processImg(String fullPath, String suffix)
	{
		ThumbnailatorUtil processer = new ThumbnailatorUtil();
		String prefix = fullPath.substring(0, fullPath.lastIndexOf(suffix));

		try
		{
			processer.init(fullPath, prefix + B_END + suffix);
			processer.scaleNormal(B_SIZE, B_SIZE, false);
			processer.init(fullPath, prefix + M_END + suffix);
			processer.scaleNormal(M_SIZE, M_SIZE, false);
			processer.init(fullPath, prefix + S_END + suffix);
			processer.scaleNormal(S_SIZE, S_SIZE, false);
		}
		catch (IOException e)
		{
			logger.error("图片读写异常", e);
			return;
		}
	}

	public static void main(String[] args)
	{
		String test = "http://blog.csdn.net/haluoluo211/article/details/52794569";
		System.out.println(test.substring(0, test.lastIndexOf("/52794569")));
		System.out.println(test.substring(test.lastIndexOf("/")));
	}
}
