package com.haozi.springdemo.fileservice.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Positions;

public class ThumbnailatorUtil extends ImageUtil
{

	@Override
	public boolean scaleNormal(int width, int height)
	{
		boolean result = false;
		try
		{
			BufferedImage image = ImageIO.read(inStream);
			Builder<BufferedImage> builder = null;
			int w = image.getWidth();
			int h = image.getHeight();
			float srcRatio = (float) w / h;
			float dstRatio = (float) width / height;
			if (w < width && h < height)
			{
				// 如果图片宽和高都小于目标图片则不做缩放处理
				builder = Thumbnails.of(image).size(w, h);
			}
			else if (srcRatio >= dstRatio)
			{
				// 按高度同比例缩放
				image = Thumbnails.of(image).height(height).asBufferedImage();
				// 裁剪
				builder = Thumbnails.of(image).sourceRegion(Positions.CENTER, width, height);

			}
			else if (srcRatio < dstRatio)
			{
				// 按宽度同比例缩放
				image = Thumbnails.of(image).width(width).asBufferedImage();
				// 裁剪
				builder = Thumbnails.of(image).sourceRegion(Positions.CENTER, width, height);
			}

			builder.scale(1f).outputQuality(0.25f).outputFormat("jpg").toOutputStream(outStream);
			result = true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scaleByHeight(int height)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
