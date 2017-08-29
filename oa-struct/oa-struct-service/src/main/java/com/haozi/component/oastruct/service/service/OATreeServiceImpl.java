/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan. 
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna. 
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus. 
 * Vestibulum commodo. Ut rhoncus gravida arcu. 
 */

package com.haozi.component.oastruct.service.service;

import com.haozi.component.oastruct.intf.OATreeService;
import com.haozi.component.oastruct.service.common.util.tree.TreeStructUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

/**
 * @className: com.haozi.component.oastruct.service.service.OATreeServiceImpl
 * @description: OA树结构service实现类
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/8/29 14:06
**/
@Service
public class OATreeServiceImpl implements OATreeService {

    @Autowired
    private TreeStructUtil treeUtil;

    /**
     * @param supDeptId
     * @Description: 查询某个公司下的所有子分公司，返回map中以deptId=supDeptId为键值对
     * @param: [supDeptId]
     * @author: wanghao/haozixiaowang@163.com
     * @date: 2017/8/29 13:49
     */
    @Override
    public Map<String, String> queryDeptTree(String supDeptId) {
        return treeUtil.getTree(supDeptId);
    }

    /**
     * @param supDeptId
     * @Description: 查询下一级子公司，返回deptId
     * @param: [supDeptId]
     * @author: wanghao/haozixiaowang@163.com
     * @date: 2017/8/29 13:55
     */
    @Override
    public Set<String> queryNextLevelDept(String supDeptId) {
        return treeUtil.getChilds(supDeptId);
    }

    @Override
    public String test(String test) {
        return test;
    }
}
