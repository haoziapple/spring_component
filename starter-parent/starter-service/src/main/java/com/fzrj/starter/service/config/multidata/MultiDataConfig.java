package com.fzrj.starter.service.config.multidata;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.fzrj.starter.service.config.MyBatisConfig;

/**
 * @className:com.fzrj.starter.service.config.multidata.MultiDataConfig
 * @description:多数据源配置(注意！当MyBatisConfig没有配置时才会生效)
 * @version:v1.0.0
 * @date:2017年8月14日 下午5:38:39
 * @author:WangHao
 */
@Configuration
@MapperScan(basePackages = "com.fzrj.starter.service.dao")
@EnableTransactionManagement
@ConditionalOnMissingBean(MyBatisConfig.class)
public class MultiDataConfig
{
	@Autowired
	private Environment env;

	/**
	 * jdbc配置前缀
	 */
	private static final String PRE = "jdbc.";

	/**
	 * 数据源key连接符
	 */
	public static final String KEY_APPENDER = "-";

	public static final String DS = "dataSource";

	/**
	 * 数据源配置参数key
	 */
	private static final String[] PROP_ARR = { "driverClassName", "url", "username", "password" };

	/**
	 * 根据数据源创建SqlSessionFactory
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception
	{
		SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
		fb.setDataSource(ds);// 指定数据源(这个必须有，否则报错)
		// 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
		fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));// 指定基包
		fb.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));// 指定xml文件位置
		return fb.getObject();
	}

	// 设定事务管理
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource)
	{
		return new DataSourceTransactionManager(dataSource);
	}

	/**
	 * @Description:使用动态数据源
	 * @return
	 * @throws Exception
	 * @version:v1.0
	 * @author:WangHao
	 * @date:2017年8月14日 下午4:58:46
	 */
	@Bean
	@Primary
	public DataSource dynamicDataSource() throws Exception
	{
		DynamicDataSource ds = new DynamicDataSource();
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(DS + KEY_APPENDER + "1", initDataSource("1"));
		targetDataSources.put(DS + KEY_APPENDER + "2", initDataSource("2"));
		targetDataSources.put(DS + KEY_APPENDER + "3", initDataSource("3"));

		// 新增数据源:
		// 1.这里增加一行targetDataSources.put()
		// 2.DataSourceSwitcher中增加pointcut和before方法
		// 3.application.properties中增加jdbc配置
		// 注意后缀名称的一致！！！

		ds.setTargetDataSources(targetDataSources);

		return ds;
	}

	/**
	 * @Description:根据配置文件初始化数据源,配置以"jdbc."开头,以自定义的dbKey结尾
	 * @param dbSuffix
	 * @return
	 * @throws Exception
	 * @version:v1.0
	 * @author:WangHao
	 * @date:2017年8月14日 下午4:48:23
	 */
	private DataSource initDataSource(String dbSuffix) throws Exception
	{
		Properties props = new Properties();
		for (String propKey : PROP_ARR)
		{
			props.put(propKey, env.getProperty(PRE + propKey + dbSuffix));
		}
		return DruidDataSourceFactory.createDataSource(props);
	}
}
