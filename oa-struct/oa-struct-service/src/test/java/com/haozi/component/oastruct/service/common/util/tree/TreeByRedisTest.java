package com.haozi.component.oastruct.service.common.util.tree;

import com.haozi.component.oastruct.service.Application;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/8/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class TreeByRedisTest {
    @Autowired
    private TreeStructUtil treeUtil;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addNode() throws Exception {
    }

    @Test
    public void getPath() throws Exception {
    }

    @Test
    public void getTree() throws Exception {
    }

    @Test
    public void getChilds() throws Exception {
    }

    @Test
    public void delNode() throws Exception {
    }

    @Test
    public void moveNode() throws Exception {
    }

    @Test
    public void moveAllChilds() throws Exception {
    }

    @Test
    public void InitTest() throws Exception {
//        treeUtil.initRoot();
//        treeUtil.addNode("1", TreeByRedis.ROOT);
//        treeUtil.addNode("2", TreeByRedis.ROOT);
//        treeUtil.addNode("3", TreeByRedis.ROOT);
//
//        treeUtil.addNode("4", "1");
//        treeUtil.addNode("5", "1");
//
//        treeUtil.addNode("6", "5");
//        treeUtil.addNode("7", "5");
//        treeUtil.addNode("8", "5");

        System.out.println(treeUtil.getTree(TreeByRedis.ROOT));
    }
}