package com.haozi.component.oastruct.service.common.util.tree;

import com.haozi.component.oastruct.service.common.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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


    @Override
    public void addNode(String node, String parent) {
        // 获取父节点的路径
        String parentPath = this.getPath(parent);

        if (StringUtils.isEmpty(parentPath)) {
            Set<String> set = new HashSet<>();
            set.add(node);

            // 获取所有需要添加子节点的节点
            String[] nodesToAdd = parentPath.substring(1).split(PATH_SPLIT);
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

    }

    @Override
    public void moveNode(String node, String targetParent, boolean inherit) {

    }

    public static void main(String[] args) {
        String s = "/1/3/4";
        String[] t = s.substring(1).split(PATH_SPLIT);
        System.out.println(t);
    }

}
