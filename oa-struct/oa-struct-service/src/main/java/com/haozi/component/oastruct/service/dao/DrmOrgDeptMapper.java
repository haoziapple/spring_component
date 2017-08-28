package com.haozi.component.oastruct.service.dao;

import java.util.List;

/**
 * Created by Administrator on 2017/8/28.
 */
public interface DrmOrgDeptMapper {
    String querySupDeptId(String deptId);

    List<String> queryDeptId(String supDeptId);
}
