package com.haozi.component.oastruct.service.common.util;

import static org.junit.Assert.*;

import com.haozi.component.oastruct.intf.bean.common.ReqBean;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.haozi.component.oastruct.service.Application;

import java.util.*;

/**
 * @className:com.haozi.component.oastruct.service.common.util.RedisUtilTest
 * @description:RedisUtil测试类
 * @version:v1.0.0
 * @date:2017年8月15日 下午3:31:24
 * @author:WangHao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class RedisUtilTest {
    private static final String KEY = "testKey3";

    private static final String VALUE = "testValue";
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        // init
        System.out.println("init set key");
        redisUtil.set(KEY, VALUE);
    }

    @After
    public void tearDown() throws Exception {
        // clear key
        System.out.println("clear key");
        redisUtil.del(KEY);
    }

    @Test
    public final void testSet() {
        redisUtil.set(KEY, "string1");
        System.out.println("testSet: " + redisUtil.get(KEY));
        assertEquals("set fail", "string1", redisUtil.get(KEY));
    }

    @Test
    public final void testGet() {
        System.out.println("testGet: " + redisUtil.get(KEY));
        assertEquals("get fail", VALUE, redisUtil.get(KEY));
    }

    @Test
    public final void testSetObj() {
        try {
            ReqBean info = new ReqBean();
            info.setId("id");
            redisUtil.set(KEY, info);
            System.out.println("testSetObj: " + redisUtil.getObj(KEY, ReqBean.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void testGetObj() {
        try {
            ReqBean info = new ReqBean();
            info.setId("id");
            redisUtil.set(KEY, info);
            System.out.println("testGetObj: " + redisUtil.get(KEY));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void testDel() {
        System.out.println("testDel: " + redisUtil.get(KEY));
        redisUtil.del(KEY);
        assertNull(redisUtil.get(KEY));
    }

    @Test
    public final void testIncr() {
        try {
            redisUtil.set(KEY, "23");
            System.out.println("testIncr: " + redisUtil.get(KEY));
            redisUtil.incr(KEY, 5L);
            assertEquals("incr fail", "28", redisUtil.get(KEY));
            System.out.println("testIncr: " + redisUtil.get(KEY));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void testSendMsg() {
        try {
            redisUtil.sendMsg(new String[]{"starter-demo"}, "testMsg-王昊");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public final void testHSet() throws Exception {
        redisUtil.hSet("tree:path_H", "4", "/1/3/4");
    }

    @Test
    public final void testHmSet() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "/1");
        map.put("2", "/1/2");
        map.put("3", "/1/3");
        try {
            redisUtil.hmSet("tree:path_H", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public final void testHGet() {
        System.out.println(redisUtil.hGet("tree:path_H", "2"));
    }

    @Test
    public final void testHmGet() {
        List<Object> fields = new ArrayList<>();
        fields.add("1");
        fields.add("2");
        fields.add("3");
        fields.add("1");
        List<Object> results = redisUtil.hmGet("tree:path_H", fields);

        for (Object o : results) {
            System.out.println(o);
        }
    }

    @Test
    public final void testHDel() {
        List<Object> fields = new ArrayList<>();
        fields.add("1");
        fields.add("2");
        fields.add("3");
        fields.add("1");
        redisUtil.hDel("tree:path_H", fields);
    }

    @Test
    public final void testSAdd() {
        Set<String> s = new HashSet<>();
        s.add("3");
        s.add("4");
        s.add("2");
        redisUtil.sAdd("tree:heirs_S:1", s);

        s.remove("2");
        redisUtil.sAdd("tree:heirs_S:2", s);
    }

    @Test
    public final void testSRem() {
        Set<String> s = new HashSet<>();
        s.add("3");
        redisUtil.sRem("tree:heirs_S:1", s);
    }

    @Test
    public final void testSMembers() {
        Set<String> s = redisUtil.sMembers("tree:heirs_S:1");
        for (String t : s) {
            System.out.println(t);
        }
    }

    @Test
    public final void testSMove() {
        System.out.println(redisUtil.sMove("tree:heirs_S:1", "2", "tree:heirs_S:2"));
    }

    @Test
    public final void testClearDB() {
        redisUtil.clearDB();
    }


}
