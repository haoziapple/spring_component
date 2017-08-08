package com.haozi.springdemo.fileservice;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.haozi.springdemo.fileservice.util.SimpleimageUtil;

public class SimpleimageUtilTest
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
	public final void test()
	{
		System.out.println("run wanghao test");
		assertEquals("11", "11");
	}

	@Test
	public final void testLoadFile()
	{
		SimpleimageUtil util = new SimpleimageUtil();
		String loadFile = util.loadFile("/static/Simpleimage.txt");
		// /static/7.jpg
		// /static/test.html
		System.out.println(loadFile);
		assertNotNull("load file is null", loadFile);
	}

}
