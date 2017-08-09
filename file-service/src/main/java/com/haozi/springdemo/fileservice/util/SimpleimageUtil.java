package com.haozi.springdemo.fileservice.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.simpleimage.ImageWrapper;
import com.alibaba.simpleimage.SimpleImageException;
import com.alibaba.simpleimage.render.CropParameter;
import com.alibaba.simpleimage.render.ScaleParameter;
import com.alibaba.simpleimage.render.ScaleParameter.Algorithm;
import com.alibaba.simpleimage.util.ImageReadHelper;

/**
 * @className:com.haozi.springdemo.fileservice.util.SimpleimageUtil
 * @description:使用simpleimage的图片操作工具类
 * @version:v1.0.0
 * @date:2017年8月8日 上午8:55:33
 * @author:WangHao
 */
public class SimpleimageUtil extends ImageUtil
{
	private static final Logger logger = LoggerFactory.getLogger(SimpleimageUtil.class);

	@Override
	public boolean scaleNormal(int width, int height, boolean cut)
	{
		boolean success = false;

		try
		{
			ImageWrapper imageWrapper = ImageReadHelper.read(this._inStream);
			int w = imageWrapper.getWidth();
			int h = imageWrapper.getHeight();

			// 1.缩放
			ScaleParameter scaleParam = new ScaleParameter(w, h, Algorithm.LANCZOS); // 缩放参数
			CropParameter cropParam = new CropParameter(0, 0, width, height);// 裁切参数
			float srcRatio = (float) w / h;
			float dstRatio = (float) width / height;// 源宽高比和目标宽高比

			if (w < width && h < height)
			{
				// 如果图片宽和高都小于目标图片则不做缩放处理
				scaleParam = new ScaleParameter(w, h, Algorithm.LANCZOS);
				cropParam = new CropParameter(0, 0, w, h);
			}
			else if (srcRatio >= dstRatio)
			{
				int newWidth = this.getWidth(w, h, height);
				scaleParam = new ScaleParameter(newWidth + 1, height, Algorithm.LANCZOS);
				cropParam = new CropParameter((newWidth - width) / 2, 0, width, height);

			}
			else if (srcRatio < dstRatio)
			{
				int newHeight = this.getHeight(w, h, width);
				scaleParam = new ScaleParameter(width, newHeight + 1, Algorithm.LANCZOS);
				cropParam = new CropParameter(0, (newHeight - height) / 2, width, height);
			}
			// PlanarImage planrImage =
			// ImageScaleHelper.scale(imageWrapper.getAsPlanarImage(),
			// scaleParam);
			// 2.裁切
			// imageWrapper = new ImageWrapper(planrImage);
			// planrImage = ImageCropHelper.crop(planrImage, cropParam);
		}
		catch (SimpleImageException e)
		{
			logger.error("SimpleImage util Exception", e);
		}

		this.finish();
		return success;
	}

	@Override
	public boolean scaleByWidth(int width)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scaleByHeight(int height)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @Description:简单的测试方法，打印resource路径里的文件
	 * @param resourcePath
	 * @return
	 * @version:v1.0
	 * @author:WangHao
	 * @date:2017年8月8日 下午3:04:41
	 */
	public String loadFile(String resourcePath)
	{
		InputStream is = this.getClass().getResourceAsStream(resourcePath);
		StringBuilder result = new StringBuilder("");

		byte bt[] = new byte[256];
		try
		{
			while (is.read(bt) != -1)
			{
				result.append(new String(bt));
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			IOUtils.closeQuietly(is);
		}

		return result.toString();
	}
	// 关闭流
}
