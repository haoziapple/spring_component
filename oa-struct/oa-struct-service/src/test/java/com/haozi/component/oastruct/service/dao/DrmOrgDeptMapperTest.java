package com.haozi.component.oastruct.service.dao;

import com.haozi.component.oastruct.service.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Administrator on 2017/8/28.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class DrmOrgDeptMapperTest {
    @Autowired
    private DrmOrgDeptMapper mapper;


    @Test
    public final void testQuerySupDeptId() {
        System.out.println(mapper.querySupDeptId("DEP170517051758336dyPQou"));
    }

    @Test
    public final void testQueryDeptId() {
        System.out.println(mapper.queryDeptId("-1"));
    }
}
