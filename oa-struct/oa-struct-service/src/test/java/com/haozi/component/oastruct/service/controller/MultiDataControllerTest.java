package com.haozi.component.oastruct.service.controller;

import static org.junit.Assert.assertNotNull;

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

import com.haozi.component.oastruct.service.Application;

/**
 * @className:com.haozi.component.oastruct.service.controller.MultiDataControllerTest
 * @description:多数据源Controller测试类
 * @version:v1.0.0
 * @date:2017年8月15日 上午9:02:59
 * @author:WangHao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class MultiDataControllerTest
{

	@Autowired
	private MultiDataController controller;

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
	public final void testTest()
	{
		int result = controller.test();

		System.out.println("testTest(): " + result);
		assertNotNull(result);
	}

}
