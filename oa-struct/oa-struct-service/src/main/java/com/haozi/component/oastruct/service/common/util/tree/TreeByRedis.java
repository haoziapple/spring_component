package com.haozi.component.oastruct.service.common.util.tree;

import com.google.gson.Gson;
import com.haozi.component.oastruct.service.common.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by Administrator on 2017/8/24.
 */
@Component
public class TreeByRedis implements TreeStructUtil {
    // 根节点名称
    public static final String ROOT = "-1";
    // 路径分隔符
    private static final String PATH_SPLIT = "/";

    // 存储所有节点路径信息的HashMap的Key
    private static final String H_PATH_KEY = "tree:path_H";

    // 存储节点所有子节点的key前缀
    private static final String S_HEIRS_KEY = "tree:heirs_S:";
    @Autowired
    private RedisUtil redisUtil;

    private static final Logger logger = LoggerFactory.getLogger(TreeByRedis.class);

    public static void main(String[] args) {
        String s = "/1/3/4";
        String[] t = s.substring(1).split(PATH_SPLIT);
        System.out.println(t);
    }

    // 初始化根节点
    @Override
    public void initRoot() {
        // 初始化路径信息， root的路径也是root
        redisUtil.hSet(H_PATH_KEY, ROOT, ROOT);
    }

    @Override
    public void addNode(String node, String parent) {
        // 子节点已经存在，不添加
        // 父节点不存在，直接返回
        if (!this.isNodeNull(node) || this.isNodeNull(parent)) {
            throw new IllegalArgumentException("node does not exist!");
        }

        // 获取父节点的路径
        String parentPath = this.getPath(parent);

        if (!StringUtils.isEmpty(parentPath)) {
            Set<String> set = new HashSet<>();
            set.add(node);

            // 获取所有需要添加子节点的节点
            String[] nodesToAdd = parentPath.split(PATH_SPLIT);
            // 循环添加
            for (String n : nodesToAdd) {
                redisUtil.sAdd(S_HEIRS_KEY + n, set);
            }

            // 添加路径信息
            redisUtil.hSet(H_PATH_KEY, node, parentPath + PATH_SPLIT + node);
        }
    }

    @Override
    public String getPath(String node) {
        return redisUtil.hGet(H_PATH_KEY, node);
    }

    @Override
    public Map<String, String> getTree(String parentNode) {
        if (this.isNodeNull(parentNode)) {
            throw new IllegalArgumentException("node does not exist!");
        }

        Map<String, String> result = new HashMap<>();

        // 获取所有子节点
        Set<String> childs = this.getChilds(parentNode);

        for (String child : childs) {
            result.put(child, this.getParent(child));
        }
        return result;
    }

    @Override
    public Set<String> getChilds(String parentNode) {
        if (this.isNodeNull(parentNode)) {
            throw new IllegalArgumentException("node does not exist!");
        }

        return redisUtil.sMembers(S_HEIRS_KEY + parentNode);
    }

    @Override
    public Set<String> getN1Childs(String parentNode) {
        if (this.isNodeNull(parentNode)) {
            throw new IllegalArgumentException("node does not exist!");
        }

        Set<String> childs = this.getChilds(parentNode);
        Iterator<String> iter = childs.iterator();

        String parentPathPrefix = this.getPath(parentNode) + PATH_SPLIT;
        String path;
        while (iter.hasNext()) {
            path = this.getPath(iter.next());

            if (path.replaceAll(parentPathPrefix, "").contains(PATH_SPLIT))
                iter.remove();
        }
        return childs;
    }

    @Override
    public String getParent(String node) {
        if (this.isNodeNull(node)) {
            throw new IllegalArgumentException("node does not exist!");
        }
        if (ROOT.equals(node))
            return null;
        String[] pathArr = this.getPath(node).split(PATH_SPLIT);
        return pathArr[pathArr.length - 2];
    }

    @Override
    public void delNode(String node, boolean inherit) {
        if (this.isNodeNull(node)) {
            throw new IllegalArgumentException("node does not exist!");
        }
        logger.info("before del node: {}, inherit: {} , tree is: {}", node, inherit, this.getTree(ROOT));
        if (inherit) {
            // 遗传性删除，所有子节点也删除
            // 删除子节点信息
            Set<String> childsSet = this.getChilds(node);
            childsSet.add(node);
            // 父节点
            String sourceParent = this.getParent(node);
            String sourcePath = this.getPath(sourceParent);
            Set<String> sourceSet = new HashSet<>(Arrays.asList(sourcePath.split(PATH_SPLIT)));

            // 删除父节点的后代信息
            for (String source : sourceSet) {
                redisUtil.sRem(S_HEIRS_KEY + source, childsSet);
            }
            // 子节点的所有后代信息直接删除
            for (String child : childsSet) {
                redisUtil.del(S_HEIRS_KEY + child);
            }

            // 删除子节点的路径信息
            List<Object> childsList = new ArrayList<>();
            Collections.addAll(childsList, childsSet.toArray());
            redisUtil.hDel(H_PATH_KEY, childsList);

        } else {
            // 非遗传性删除，所有子节点挂到上一级的父节点下
            // 先将所有子节点上挂一级
            this.moveAllChilds(node, this.getParent(node));

            // 再删除这单个节点
            this.delNode(node, true);
        }
        logger.info("after del node: {}, inherit: {} , tree is: {}", node, inherit, this.getTree(ROOT));
    }

    @Override
    public void moveNode(String node, String targetParent, boolean inherit) {
        if (this.isNodeNull(node) || this.isNodeNull(targetParent)) {
            throw new IllegalArgumentException("node does not exist!");
        }
        logger.info("before move node: {}, targetParent: {} , inherit: {}, tree is: {}", node, targetParent, inherit, this.getTree(ROOT));
        if (inherit) {
            String sourceParent = this.getParent(node);
            String sourcePath = this.getPath(sourceParent);
            String targetPath = this.getPath(targetParent);

            // 遗传性移动，所有子节点跟随一起移动
            Set<String> sourceSet = new HashSet<>(Arrays.asList(sourcePath.split(PATH_SPLIT)));
            Set<String> targetSet = new HashSet<>(Arrays.asList(targetPath.split(PATH_SPLIT)));

            // 求交集, 交集里节点的子节点不需变更
            Set<String> commonSet = new HashSet<>();
            commonSet.clear();
            commonSet.addAll(sourceSet);
            commonSet.retainAll(targetSet);

            // 更新子节点信息
            sourceSet.removeAll(commonSet);
            targetSet.removeAll(commonSet);
            Set<String> childsSet = this.getChilds(node);
            childsSet.add(node);
            for (String source : sourceSet) {
                redisUtil.sRem(S_HEIRS_KEY + source, childsSet);
            }
            for (String target : targetSet) {
                redisUtil.sAdd(S_HEIRS_KEY + target, childsSet);
            }

            // 更新路径信息
            Map<String, String> pathMap = new HashMap<>();
            for (String child : childsSet) {
                pathMap.put(child, this.getPath(child).replaceFirst(sourcePath, targetPath));
            }

            redisUtil.hmSet(H_PATH_KEY, pathMap);
        } else {
            // 非遗传性移动，所有子节点挂到上一级的父节点下

            // 先将所有子节点上挂一级
            this.moveAllChilds(node, this.getParent(node));

            // 再移动节点
            this.moveNode(node, targetParent, true);
        }
        logger.info("after move node: {}, targetParent: {} , inherit: {}, tree is: {}", node, targetParent, inherit, this.getTree(ROOT));
    }

    // 将某个节点的所有子节点移动到新的父节点下面
    @Override
    public void moveAllChilds(String sourceNode, String targetNode) {
        if (this.isNodeNull(sourceNode) || this.isNodeNull(targetNode)) {
            throw new IllegalArgumentException("node does not exist!");
        }
        String sourcePath = this.getPath(sourceNode);
        String targetPath = this.getPath(targetNode);
        Set<String> sourceSet = new HashSet<>(Arrays.asList(sourcePath.split(PATH_SPLIT)));
        Set<String> targetSet = new HashSet<>(Arrays.asList(targetPath.split(PATH_SPLIT)));

        // 求交集, 交集里节点的子节点不需变更
        Set<String> commonSet = new HashSet<>();
        commonSet.clear();
        commonSet.addAll(sourceSet);
        commonSet.retainAll(targetSet);

        // 更新子节点信息
        sourceSet.removeAll(commonSet);
        targetSet.removeAll(commonSet);
        Set<String> childsSet = this.getChilds(sourceNode);

        if (!childsSet.isEmpty()) {
            for (String source : sourceSet) {
                redisUtil.sRem(S_HEIRS_KEY + source, childsSet);
            }
            for (String target : targetSet) {
                redisUtil.sAdd(S_HEIRS_KEY + target, childsSet);
            }
        }

        // 更新路径信息
        Map<String, String> pathMap = new HashMap<>();
        if (!childsSet.isEmpty()) {
            for (String child : childsSet) {
                pathMap.put(child, this.getPath(child).replaceFirst(sourcePath, targetPath));
            }
            redisUtil.hmSet(H_PATH_KEY, pathMap);
        }
    }

    // 校验node是否存在
    private boolean isNodeNull(String node) {
        return StringUtils.isEmpty(this.getPath(node));
    }

}
