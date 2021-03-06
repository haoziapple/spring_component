package com.haozi.springdemo.fileservice.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

public abstract class ImageUtil
{
	protected FileInputStream inStream = null;
	protected FileOutputStream outStream = null;
	protected String srcPath;
	protected String targetPath;

	/**
	 * @Description:初始化输入输出文件(绝对路径)
	 * @param inputFile
	 * @param outputFile
	 * @version:v1.0
	 * @author:WangHao
	 * @throws IOException
	 * @date:2017年8月8日 下午3:04:26
	 */
	public void init(String src, String target) throws IOException
	{
		this.srcPath = src;
		this.targetPath = target;
		inStream = new FileInputStream(src);
		outStream = new FileOutputStream(target);
	}

	/**
	 * @Description:等比例缩放，是否裁剪
	 * @param width
	 * @param height
	 * @return
	 * @version:v1.0
	 * @author:WangHao
	 * @date:2017年8月8日 下午4:53:56
	 */
	public abstract boolean scaleNormal(int width, int height, boolean cut);

	/**
	 * @Description:缩放到指定宽度，高度自适应
	 * @param width
	 * @return
	 * @version:v1.0
	 * @author:WangHao
	 * @date:2017年8月8日 下午4:56:26
	 */
	public abstract boolean scaleByWidth(int width);

	/**
	 * @Description:缩放到指定高度，宽度自适应
	 * @param height
	 * @return
	 * @version:v1.0
	 * @author:WangHao
	 * @date:2017年8月8日 下午4:57:37
	 */
	public abstract boolean scaleByHeight(int height);

	// 关闭文件流
	protected void finish()
	{
		IOUtils.closeQuietly(inStream);
		IOUtils.closeQuietly(outStream);

	}

	// 根据宽，高和目标宽度 等比例求高度
	protected Integer getHeight(Integer w, Integer h, Integer width)
	{
		return h * width / w;
	}

	// 根据狂傲和目标高度，等比例求宽度
	protected Integer getWidth(Integer w, Integer h, Integer height)
	{
		return w * height / h;
	}
}
