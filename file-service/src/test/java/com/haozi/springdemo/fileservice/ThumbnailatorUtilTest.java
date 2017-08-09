package com.haozi.springdemo.fileservice;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import com.haozi.springdemo.fileservice.util.ThumbnailatorUtil;

public class ThumbnailatorUtilTest
{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public final void testScaleNormal()
	{
		try
		{
			String sourceFile = "F:\\gitProject\\spring_demo\\file-service\\src\\main\\resources\\static\\16.jpg";
			String targetFile = "F:\\scaleNormal.jpg";
			int w = 300;
			int h = 300;
			ThumbnailatorUtil util = new ThumbnailatorUtil();
			util.init(sourceFile, targetFile);
			boolean result = util.scaleNormal(300, 300);
			assertTrue("图片处理失败", result);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		// Thumbnails.of(targetFile).
	}

	@Test
	public final void testScaleByWidth()
	{
		try
		{
			String sourceFile = "F:\\gitProject\\spring_demo\\file-service\\src\\main\\resources\\static\\16.jpg";
			String targetFile = "F:\\scaleByWidth.jpg";
			int w = 300;
			ThumbnailatorUtil util = new ThumbnailatorUtil();
			util.init(sourceFile, targetFile);
			boolean result = util.scaleByWidth(w);
			assertTrue("图片处理失败", result);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test
	public final void testScaleByHeight()
	{
		try
		{
			String sourceFile = "F:\\gitProject\\spring_demo\\file-service\\src\\main\\resources\\static\\16.jpg";
			String targetFile = "F:\\scaleByHeight.jpg";
			int h = 300;
			ThumbnailatorUtil util = new ThumbnailatorUtil();
			util.init(sourceFile, targetFile);
			boolean result = util.scaleByHeight(h);
			assertTrue("图片处理失败", result);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
