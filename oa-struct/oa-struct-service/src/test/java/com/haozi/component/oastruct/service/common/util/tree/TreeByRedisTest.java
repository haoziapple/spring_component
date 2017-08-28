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
        System.out.println(treeUtil.getChilds(TreeByRedis.ROOT));
    }

    @Test
    public void getN1Childs() throws Exception {
        System.out.println(treeUtil.getN1Childs(TreeByRedis.ROOT));
    }

    @Test
    public void getParent() throws Exception {
        System.out.println("3 node parent: " + treeUtil.getParent("3"));
        System.out.println("5 node parent: " + treeUtil.getParent("5"));
        System.out.println("8 node parent: " + treeUtil.getParent("8"));
        // 尝试获取不存在的节点的父类
//        System.out.println("9 node parent: " + treeUtil.getParent("9"));
    }

    @Test
    public void delNode() throws Exception {
        System.out.println("before del, total tree: " + treeUtil.getTree(TreeByRedis.ROOT));
        treeUtil.delNode("-1", true);
        System.out.println("after del, total tree: " + treeUtil.getTree(TreeByRedis.ROOT));
    }

    @Test
    public void moveNode() throws Exception {
        System.out.println("before move, total tree: " + treeUtil.getTree(TreeByRedis.ROOT));
        treeUtil.moveNode("DEP1", "root", true);
        System.out.println("after move, total tree: " + treeUtil.getTree(TreeByRedis.ROOT));
    }

    @Test
    public void moveAllChilds() throws Exception {
        System.out.println("before move, 5 childs: " + treeUtil.getChilds("5"));
        System.out.println("before move, 2 childs: " + treeUtil.getChilds("2"));
        treeUtil.moveAllChilds("5", "2");
        System.out.println("after move, 5 childs: " + treeUtil.getChilds("5"));
        System.out.println("after move, 2 childs: " + treeUtil.getChilds("2"));
    }

    @Test
    public void InitTest() throws Exception {
//        treeUtil.initRoot();
//        treeUtil.addNode("1", TreeByRedis.ROOT);
//        treeUtil.addNode("2", TreeByRedis.ROOT);
//        treeUtil.addNode("3", TreeByRedis.ROOT);
//
//        treeUtil.moveNode("4", "1", false);
//        treeUtil.moveNode("5", "1", false);
//
//        treeUtil.addNode("6", "5");
//        treeUtil.addNode("7", "5");
//        treeUtil.addNode("8", "5");

        System.out.println(treeUtil.getTree(TreeByRedis.ROOT));
    }
}