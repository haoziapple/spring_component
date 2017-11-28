package com.fzrj.starter.service.sandbox;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.ognl.OgnlContext;
import org.apache.ibatis.ognl.OgnlException;
import org.apache.ibatis.ognl.OgnlRuntime;
import org.apache.ibatis.ognl.PropertyAccessor;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.xmltags.DynamicContext;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2017/11/28.
 */
@Intercepts({
        @Signature(method = "update", type = Executor.class, args = {MappedStatement.class, Object.class}),
        @Signature(method = "query", type = Executor.class, args = {MappedStatement.class, Object.class, RowBounds.class,
                ResultHandler.class, CacheKey.class, BoundSql.class}),
        @Signature(method = "query", type = Executor.class, args = {MappedStatement.class, Object.class, RowBounds.class,
                ResultHandler.class})})
public class MybatisVariablePlugin implements Interceptor {
    public static final String PARAMETER_OBJECT_KEY = "_parameter";
    public static final String DATABASE_ID_KEY = "_databaseId";
    public static final String BRAND_KEY = "_brand_";

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private volatile boolean inited = false;

    private void init() {
        if (!inited) {
            synchronized (this) {
                Configuration configuration = new Configuration();
                configuration.setDatabaseId("xxx");
                OgnlRuntime.setPropertyAccessor(new DynamicContext(configuration, null).getBindings().getClass(), new ContextAccessor());
                inited = true;
            }
        }
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        init();
        Object ret = invocation.proceed();
        return ret;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    static class ContextAccessor implements PropertyAccessor {
        @SuppressWarnings("rawtypes")
        public Object getProperty(Map context, Object target, Object name) throws OgnlException {
            if (name.equals(BRAND_KEY)) {
                return GlobalConfigStorage.getBrand().getValue();
            }

            Map map = (Map) target;

            Object result = map.get(name);
            if (result != null) {
                return result;
            }

            Object parameterObject = map.get(PARAMETER_OBJECT_KEY);
            if (parameterObject instanceof Map) {
                return ((Map) parameterObject).get(name);
            }

            return null;
        }

        @SuppressWarnings({"rawtypes", "unchecked"})
        public void setProperty(Map context, Object target, Object name, Object value) throws OgnlException {
            Map map = (Map) target;
            map.put(name, value);
        }

        @Override
        public String getSourceAccessor(OgnlContext arg0, Object arg1,
                                        Object arg2) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getSourceSetter(OgnlContext arg0, Object arg1, Object arg2) {
            // TODO Auto-generated method stub
            return null;
        }
    }
}
