package com.haozi.component.oastruct.service.common.util.tree;

import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/8/24.
 * 树形结构操作工具类
 */
public interface TreeStructUtil {
    // 添加一个节点
    void addNode(String node, String parent);

    // 获取一个节点的路径字串,以“/”分隔
    String getPath(String node);

    // 获取一个节点下的子树, 以id为key，以pid为value
    Map<String, String> getTree(String parentNode);

    // 获取一个节点下的所有子节点
    Set<String> getChilds(String parentNode);

    // 删除一个节点
    void delNode(String node, boolean inherit);

    // 移动一个节点
    void moveNode(String node, String targetParent, boolean inherit);

    // 初始化根节点
    void initRoot();

    // 移动所有子节点
    void moveAllChilds(String sourceNode, String targetNode);
}
