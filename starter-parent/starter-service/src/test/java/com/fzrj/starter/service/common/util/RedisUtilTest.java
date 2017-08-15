package com.fzrj.starter.service.common.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fzrj.starter.intf.bean.order.SubmitOrderInfo;
import com.fzrj.starter.service.Application;

/**
 * @className:com.fzrj.starter.service.common.util.RedisUtilTest
 * @description:RedisUtil测试类
 * @version:v1.0.0
 * @date:2017年8月15日 下午3:31:24
 * @author:WangHao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class RedisUtilTest
{
	private static final String KEY = "testKey3";

	private static final String VALUE = "testValue";
	@Autowired
	private RedisUtil redisUtil;

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
		// init
		System.out.println("init set key");
		redisUtil.set(KEY, VALUE);
	}

	@After
	public void tearDown() throws Exception
	{
		// clear key
		System.out.println("clear key");
		redisUtil.del(KEY);
	}

	@Test
	public final void testSet()
	{
		redisUtil.set(KEY, "string1");
		System.out.println("testSet: " + redisUtil.get(KEY));
		assertEquals("set fail", "string1", redisUtil.get(KEY));
	}

	@Test
	public final void testGet()
	{
		System.out.println("testGet: " + redisUtil.get(KEY));
		assertEquals("get fail", VALUE, redisUtil.get(KEY));
	}

	@Test
	public final void testSetObj()
	{
		try
		{
			redisUtil.set(KEY, new SubmitOrderInfo()
			{
				{
					setId("testId");
					setToken("testToken");
				}
			});
			System.out.println("testSetObj: " + redisUtil.getObj(KEY));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test
	public final void testGetObj()
	{
		try
		{
			redisUtil.set(KEY, new SubmitOrderInfo()
			{
				{
					setId("testId");
					setToken("testToken");
				}
			});
			System.out.println("testGetObj: " + redisUtil.get(KEY));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test
	public final void testDel()
	{
		System.out.println("testDel: " + redisUtil.get(KEY));
		redisUtil.del(KEY);
		assertNull(redisUtil.get(KEY));
		assertNull(redisUtil.getObj(KEY));
	}

	@Test
	public final void testIncr()
	{
		try
		{
			redisUtil.set(KEY, "23");
			System.out.println("testIncr: " + redisUtil.get(KEY));
			redisUtil.incr(KEY, 5L);
			assertEquals("incr fail", "28", redisUtil.get(KEY));
			System.out.println("testIncr: " + redisUtil.get(KEY));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
