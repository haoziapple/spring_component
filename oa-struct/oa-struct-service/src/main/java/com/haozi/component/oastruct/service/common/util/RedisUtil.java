package com.haozi.component.oastruct.service.common.util;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @className:com.haozi.component.oastruct.service.common.util.RedisUtil
 * @description:redis工具类,通过注入使用,方法待丰富,对象序列化使用Gson
 * @version:v1.0.0
 * @date:2017年5月17日 下午4:41:33
 * @author:WangHao
 */
@Component
public class RedisUtil {
    public static final Gson gson = new GsonBuilder().create();

    // 操作String类型
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> stringOps;

    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    /**
     * @param key
     * @param value
     * @Description:set操作
     * @version:v1.0
     * @author:WangHao
     * @date:2017年8月16日 上午10:41:39
     */
    public void set(String key, String value) {
        stringOps.set(key, value);
    }

    /**
     * @param key
     * @return
     * @Description:get操作
     * @version:v1.0
     * @author:WangHao
     * @date:2017年8月16日 上午10:42:08
     */
    public String get(String key) {
        return stringOps.get(key);
    }

    /**
     * @param key
     * @param delta
     * @Description:自增操作
     * @version:v1.0
     * @author:WangHao
     * @date:2017年8月16日 上午10:42:17
     */
    public void incr(String key, Long delta) {
        stringOps.increment(key, delta);
    }

    /**
     * @param key
     * @Description:删除指定key
     * @version:v1.0
     * @author:WangHao
     * @date:2017年8月16日 上午10:42:29
     */
    public void del(String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * @param key
     * @param value
     * @Description:set Object, 底层调用Gson序列化与set方法
     * @version:v1.0
     * @author:WangHao
     * @date:2017年8月16日 上午10:43:28
     */
    public void set(String key, Object value) {
        this.set(key, gson.toJson(value));
    }

    /**
     * @param key
     * @param classOfT
     * @return
     * @Description:get Object, 底层调用Gson序列化与get方法
     * @version:v1.0
     * @author:WangHao
     * @date:2017年8月16日 上午10:43:55
     */
    public <T> T getObj(String key, Class<T> classOfT) {
        return gson.fromJson(this.get(key), classOfT);
    }

    /**
     * @param topics
     * @param msg
     * @Description:发布消息
     * @version:v1.0
     * @author:WangHao
     * @date:2017年8月16日 上午11:13:47
     */
    public void sendMsg(String[] topics, Object msg) {
        for (String topic : topics) {
            stringRedisTemplate.convertAndSend(topic, gson.toJson(msg));
        }
    }

    // HashMap，设置单个key
    public void hSet(String key, String field, String value) {
        stringRedisTemplate.opsForHash().put(key, field, value);
    }

    // HashMap，设置多个key
    public void hmSet(String key, Map<String, String> map) {
        stringRedisTemplate.opsForHash().putAll(key, map);
    }

    // HashMap, 获取单个value值
    public String hGet(String key, String field) {
        return (String) stringRedisTemplate.opsForHash().get(key, field);
    }

    // HashMap, 获取多个value值
    public List<Object> hmGet(String key, List<Object> fields) {
        return stringRedisTemplate.opsForHash().multiGet(key, fields);
    }

    // HashMap, 删除map中多个fields
    public void hDel(String key, List<Object> fields) {
        stringRedisTemplate.opsForHash().delete(key, fields.toArray());
    }

    // Set, 将一个或多个member元素加入到集合key当中，已经存在于集合的member元素将被忽略
    public void sAdd(String key, Set<String> members) {
        stringRedisTemplate.opsForSet().add(key, members.toArray(new String[members.size()]));
    }

    // Set, 移除集合key中的一个或多个member元素，不存在的member元素会被忽略
    public void sRem(String key, Set<String> members) {
        stringRedisTemplate.opsForSet().remove(key, members.toArray(new String[members.size()]));
    }

    // Set, 返回集合key中的所有成员
    public Set<String> sMembers(String key) {
        return stringRedisTemplate.opsForSet().members(key);
    }

    // Set, 判断member元素是否是集合key的成员
    public boolean sIsMember(String key, String member) {
        return stringRedisTemplate.opsForSet().isMember(key, member);
    }

    // Set, 将member元素从source集合移动到destination集合
    public boolean sMove(String key, String value, String destKey) {
        return stringRedisTemplate.opsForSet().move(key, value, destKey);
    }

    // 清空redis，慎用！
    public void clearDB() {
        Set<String> keys = stringRedisTemplate.keys("*");
        logger.info("keys to delete: {}", keys);
        stringRedisTemplate.delete(keys);
    }

}
