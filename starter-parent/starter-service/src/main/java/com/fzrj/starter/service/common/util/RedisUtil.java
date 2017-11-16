package com.fzrj.starter.service.common.util;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @className:com.fzrj.starter.service.common.util.RedisUtil
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
    // 操作Object类型,暂不在此工具类中使用
    @Autowired
    RedisTemplate<String, Object> objRedisTemplate;
    @Resource(name = "objRedisTemplate")
    ValueOperations<String, Object> objOps;

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

}
