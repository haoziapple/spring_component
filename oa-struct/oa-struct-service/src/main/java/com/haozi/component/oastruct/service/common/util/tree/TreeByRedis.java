package com.haozi.component.oastruct.service.common.util.tree;

import com.google.gson.Gson;
import com.haozi.component.oastruct.intf.bean.order.SubmitOrderInfo;
import com.haozi.component.oastruct.service.common.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by Administrator on 2017/8/24.
 */
@Component
public class TreeByRedis implements TreeStructUtil {
    @Autowired
    private RedisUtil redisUtil;

    // 路径分隔符
    private static final String PATH_SPLIT = "/";

    // 存储所有节点路径信息的HashMap的Key
    private static final String H_PATH_KEY = "tree:path_H";

    // 存储节点所有子节点的key前缀
    private static final String S_HEIRS_KEY = "tree:heirs_S:";

    // 根节点名称
    public static final String ROOT = "root";

    // 初始化根节点
    @Override
    public void initRoot() {
        // 初始化路径信息， root的路径也是root
        redisUtil.hSet(H_PATH_KEY, ROOT, ROOT);
    }

    @Override
    public void addNode(String node, String parent) {
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
        Map<String, String> result = new HashMap<>();

        // 获取所有子节点
        Set<String> childs = this.getChilds(parentNode);

        for (String child : childs) {
            String[] pathArr = this.getPath(child).split(PATH_SPLIT);
            String parent = pathArr[pathArr.length - 2];
            result.put(child, parent);
        }
        return result;
    }

    @Override
    public Set<String> getChilds(String parentNode) {
        return redisUtil.sMembers(S_HEIRS_KEY + parentNode);
    }

    @Override
    public void delNode(String node, boolean inherit) {
        if (inherit) {
            // 遗传性删除，所有子节点也删除
            // TODO
        } else {
            // 非遗传性删除，所有子节点挂到上一级的父节点下
            // TODO
        }
    }

    @Override
    public void moveNode(String node, String targetParent, boolean inherit) {
        if (inherit) {
            // 遗传性移动，所有子节点跟随一起移动
            // TODO
        } else {
            // 非遗传性移动，所有子节点挂到上一级的父节点下
            // TODO
        }
    }

    // 将某个节点的所有子节点移动到新的父节点下面
    @Override
    public void moveAllChilds(String sourceNode, String targetNode) {
        Set<String> sourceSet = new HashSet<>(Arrays.asList(this.getPath(sourceNode).split(PATH_SPLIT)));
        Set<String> targetSet= new HashSet<>(Arrays.asList(this.getPath(sourceNode).split(PATH_SPLIT)));

        // 求交集, 交集里节点的子节点不需变更
        Set<String> commonSet = new HashSet<>();
        commonSet.clear();
        commonSet.addAll(sourceSet);
        commonSet.retainAll(targetSet);

        sourceSet.removeAll(commonSet);
        targetSet.removeAll(commonSet);
        Set<String> childsSet = this.getChilds(sourceNode);
        for(String source:sourceSet)
        {
            redisUtil.sAdd(S_HEIRS_KEY+source,childsSet);
        }
        for(String target:targetSet)
        {
            redisUtil.sRem(S_HEIRS_KEY+target,childsSet);
        }

        String sourcePath = this.getPath(sourceNode);
        String targetPath = this.getPath(targetNode);

        Map<String, String> pathMap = new HashMap<>();
        for(String child:childsSet)
        {
            pathMap.put(child,this.getPath(child).replaceFirst(sourcePath, targetPath));
        }

        redisUtil.hmSet(H_PATH_KEY,pathMap);

    }


    public static void main(String[] args) {
        String s = "/1/3/4";
        String[] t = s.substring(1).split(PATH_SPLIT);
        System.out.println(t);

        Gson gson = new Gson();
        String jsonStr = gson.toJson(new SubmitOrderInfo() {
            {
                setId("id");
                setToken("token");
                setUserId("userId");
                setTotalPrice("totalPrice");
            }
        }, SubmitOrderInfo.class);

        System.out.println(jsonStr);
    }
}
