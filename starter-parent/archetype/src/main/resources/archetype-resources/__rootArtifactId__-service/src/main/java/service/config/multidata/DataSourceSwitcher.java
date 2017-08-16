#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.config.multidata;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import ${package}.service.config.MyBatisConfig;

/**
 * @className:${package}.service.config.multidata.DataSourceSwitcher
 * @description:使用aop动态切换数据源(注意！当MyBatisConfig没有配置时才会生效)
 * @version:v1.0.0
 * @date:2017年8月14日 下午5:37:40
 * @author:WangHao
 */
@Aspect
@Component
@ConditionalOnMissingBean(MyBatisConfig.class)
public class DataSourceSwitcher
{
	private static final Logger logger = LoggerFactory.getLogger(DataSourceSwitcher.class);

	// 使用ThreadLocal保存db的key值
	private static final ThreadLocal<String> dbKeyHolder = new ThreadLocal<>();

	// 数据源1切面配置
	@Pointcut(value = "execution(* ${package}.service.service.data1..*.*(..))")
	public void pointcut1()
	{
		// 定义切点1，切点处调用before1方法切换数据源
	}

	@Before(value = "pointcut1()")
	public void before1(JoinPoint joinPoint)
	{
		logger.debug("use datasource-1");
		setDbKey(MultiDataConfig.DS + MultiDataConfig.KEY_APPENDER + "1");
	}

	// 数据源2切面配置
	@Pointcut(value = "execution(* ${package}.service.service.data2..*.*(..))")
	public void pointcut2()
	{
		// 定义切点2，切点处调用before2方法切换数据源
	}

	@Before(value = "pointcut2()")
	public void before2(JoinPoint joinPoint)
	{
		logger.debug("use datasource-2");
		setDbKey(MultiDataConfig.DS + MultiDataConfig.KEY_APPENDER + "2");
	}

	// 数据源3切面配置
	@Pointcut(value = "execution(* ${package}.service.service.data3..*.*(..))")
	public void pointcut3()
	{
		// 定义切点3，切点处调用before3方法切换数据源
	}

	@Before(value = "pointcut3()")
	public void before3(JoinPoint joinPoint)
	{
		logger.debug("use datasource-3");
		setDbKey(MultiDataConfig.DS + MultiDataConfig.KEY_APPENDER + "3");
	}

	public static void setDbKey(String dbKey)
	{
		dbKeyHolder.set(dbKey);
	}

	public static String getDbKey()
	{
		return dbKeyHolder.get();
	}

	public static void clearDbKey()
	{
		dbKeyHolder.remove();
	}
}
