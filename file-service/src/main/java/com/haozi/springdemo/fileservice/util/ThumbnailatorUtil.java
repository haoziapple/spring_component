package com.haozi.springdemo.fileservice.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.haozi.springdemo.fileservice.common.SysErrMsg;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Positions;

public class ThumbnailatorUtil extends ImageUtil
{
	// 后缀名分隔符
	private static final String SPLIT = ".";

	// 最终生成时图片质量
	private static final float QUALITY = 0.5f;

	// 最终生成时缩放比例
	private static final double SCALE = 1f;

	private static final Logger logger = LoggerFactory.getLogger(ThumbnailatorUtil.class);

	@Override
	public boolean scaleNormal(int width, int height, boolean cut)
	{
		boolean result = false;
		try
		{
			BufferedImage image = ImageIO.read(this.inStream);
			Builder<BufferedImage> builder = null;
			int w = image.getWidth();
			int h = image.getHeight();
			float srcRatio = (float) w / h;
			float dstRatio = (float) width / height;
			if (w < width && h < height)
			{
				// 如果图片宽和高都小于目标图片则不做缩放处理
				image = Thumbnails.of(image).size(w, h).asBufferedImage();
				builder = Thumbnails.of(image);
			}
			else if (srcRatio >= dstRatio)
			{
				// 按高度同比例缩放
				image = Thumbnails.of(image).height(height).asBufferedImage();
				// 裁剪
				if (cut)
					builder = Thumbnails.of(image).sourceRegion(Positions.CENTER, width, height);
				else
					builder = Thumbnails.of(image);

			}
			else if (srcRatio < dstRatio)
			{
				// 按宽度同比例缩放
				image = Thumbnails.of(image).width(width).asBufferedImage();
				// 裁剪
				if (cut)
					builder = Thumbnails.of(image).sourceRegion(Positions.CENTER, width, height);
				else
					builder = Thumbnails.of(image);
			}

			if (builder != null)
			{
				buildTargetImg(builder);
				result = true;
			}
		}
		catch (IOException e)
		{
			logger.error(SysErrMsg.IMG_IO_ERR, e);
		}
		finally
		{
			this.finish();
		}
		return result;
	}

	@Override
	public boolean scaleByWidth(int width)
	{
		boolean result = false;

		try
		{
			// 按宽度同比例缩放
			BufferedImage image = Thumbnails.of(this.inStream).width(width).asBufferedImage();
			Builder<BufferedImage> builder = Thumbnails.of(image);
			buildTargetImg(builder);
			result = true;
		}
		catch (IOException e)
		{
			logger.error(SysErrMsg.IMG_IO_ERR, e);
		}
		finally
		{
			this.finish();
		}

		return result;
	}

	@Override
	public boolean scaleByHeight(int height)
	{
		boolean result = false;

		try
		{
			// 按高度同比例缩放
			BufferedImage image = Thumbnails.of(this.inStream).height(height).asBufferedImage();
			Builder<BufferedImage> builder = Thumbnails.of(image);
			buildTargetImg(builder);
			result = true;
		}
		catch (IOException e)
		{
			logger.error(SysErrMsg.IMG_IO_ERR, e);
		}
		finally
		{
			this.finish();
		}

		return result;
	}

	// 生成最终目标图片
	private void buildTargetImg(Builder<BufferedImage> builder) throws IOException
	{
		builder.scale(SCALE).outputQuality(QUALITY)
				.outputFormat(this.targetPath.substring(this.targetPath.lastIndexOf(SPLIT) + 1))
				.toOutputStream(this.outStream);
	}
}
