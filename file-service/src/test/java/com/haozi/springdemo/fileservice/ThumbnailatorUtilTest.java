package com.haozi.springdemo.fileservice;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
	public final void test() throws FileNotFoundException
	{
		ThumbnailatorUtil util = new ThumbnailatorUtil();
		util.init("F:\\gitProject\\spring_demo\\file-service\\src\\main\\resources\\static\\16.jpg", "F:\\test.jpg");
		util.scaleNormal(700, 700);
	}

}
