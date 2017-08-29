package com.haozi.component.oastruct.intf;

import java.util.Map;
import java.util.Set;

/**
 * @className: com.haozi.component.oastruct.intf.OATreeService
 * @description: OA树结构service
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/8/29 13:50
 **/
public interface OATreeService {

    /**
     * @Description: 查询某个公司下的所有子分公司，返回map中以deptId=supDeptId为键值对
     * @param: [supDeptId]
     * @author: wanghao/haozixiaowang@163.com
     * @date: 2017/8/29 13:49
     **/
    Map<String, String> queryDeptTree(String supDeptId);

    /**
     * @Description: 查询下一级子公司，返回deptId
     * @param: [supDeptId]
     * @author: wanghao/haozixiaowang@163.com
     * @date: 2017/8/29 13:55
     **/
    Set<String> queryNextLevelDept(String supDeptId);

    String test(String test);
}
