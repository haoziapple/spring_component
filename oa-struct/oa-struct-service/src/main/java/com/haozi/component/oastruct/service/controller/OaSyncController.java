package com.haozi.component.oastruct.service.controller;

import com.haozi.component.oastruct.service.common.util.tree.TreeByRedis;
import com.haozi.component.oastruct.service.common.util.tree.TreeStructUtil;
import com.haozi.component.oastruct.service.dao.DrmOrgDeptMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/8/28.
 * 定时轮询同步oa数据
 */

@Controller
@RequestMapping("/oaSync")
public class OaSyncController {
    private static final Logger logger = LoggerFactory.getLogger(OaSyncController.class);

    private List<String> toAsyncNodes = new ArrayList<>();

    private String syncId;

    @Autowired
    private TreeStructUtil treeUtil;

    @Autowired
    private DrmOrgDeptMapper mapper;

    // 同步OA系统的部门树形结构
    // 服务启动后延迟10秒，间隔5分钟执行
//    @Scheduled(fixedDelay = 5 * 6 * 10000L, initialDelay = 10000L)
    public void SyncDeptTree() {
        logger.info("SyncDeptTree started at {}", new Date());
        this.toAsyncNodes.add(TreeByRedis.ROOT);
        this.syncId = TreeByRedis.ROOT;

        while (this.toAsyncNodes != null && !this.toAsyncNodes.isEmpty()) {
            this.syncData(this.syncId, mapper.queryDeptId(this.syncId));
        }

        logger.info("SyncDeptTree finished at {}, tree is {}", new Date(), treeUtil.getTree(TreeByRedis.ROOT));
    }

    private void syncData(String supId, List<String> deptIds) {
        // 判断是否需要初始化根节点
        if (TreeByRedis.ROOT.equals(supId) && StringUtils.isEmpty((treeUtil.getPath(supId)))) {
            treeUtil.initRoot();
        }

        for (String dept : deptIds) {
            String path = treeUtil.getPath(dept);

            if (StringUtils.isEmpty(path)) {
                // 节点不存在，添加节点
                treeUtil.addNode(dept, supId);
            } else if (!supId.equals(treeUtil.getParent(dept))) {
                // 节点存在,但父节点不一致,移动节点
                treeUtil.moveNode(dept, supId, true);
            } else {
                logger.info("tree data already synchronized: deptId: {}, supId: {}", dept, supId);
            }
            this.toAsyncNodes.add(dept);
        }
        this.toAsyncNodes.remove(supId);
        if (this.toAsyncNodes != null && !this.toAsyncNodes.isEmpty()) {
            this.syncId = this.toAsyncNodes.get(0);
        }
    }
}
