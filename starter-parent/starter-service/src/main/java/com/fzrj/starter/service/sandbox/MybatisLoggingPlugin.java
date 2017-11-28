package com.fzrj.starter.service.sandbox;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * mybatis 日志插件
 *
 * @className: com.fzrj.starter.service.sandbox.MybatisLoggingPlugin
 * @description: TODO 学习mybatis插件的开发,整合入springboot中的方式
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/11/28 17:26
 **/

/**
 * @className: com.fzrj.starter.service.sandbox.MybatisLoggingPlugin
 * @description: TODO 学习mybatis插件的开发,整合入springboot中的方式
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/11/28 17:26
 **/

/**
 * 使用方法：mybatis-config.xml中添加
 *  <plugins>
 <plugin interceptor="com.fzrj.starter.service.sandbox.MybatisLoggingPlugin">
 </plugin>
 </plugins>
 */
@Intercepts({
        @Signature(method = "update", type = Executor.class, args = {MappedStatement.class, Object.class}),
        @Signature(method = "query", type = Executor.class, args = {MappedStatement.class, Object.class, RowBounds.class,
                ResultHandler.class, CacheKey.class, BoundSql.class}),
        @Signature(method = "query", type = Executor.class, args = {MappedStatement.class, Object.class, RowBounds.class,
                ResultHandler.class})})
public class MybatisLoggingPlugin implements Interceptor {
    Logger logger = LoggerFactory.getLogger("data");

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object argument = invocation.getArgs()[1];
        logger.debug("Dao: sql入参=======>> {}{}", mappedStatement.getId(), new LoggingValueWrapper(argument));
        Object ret = invocation.proceed();
        logger.debug("Dao return:sql结果=======>>> {}", new LoggingValueWrapper(ret));
        return ret;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
