#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * @className:${package}.service.config.MyBatisConfig
 * @description:mybatis配置bean，使用多数据源时将@Configuration注释掉(请谨慎使用多数据源!)
 * @version:v1.0.0
 * @date:2017年5月18日 下午1:21:05
 * @author:WangHao
 */
@Configuration
@MapperScan(basePackages = "${package}.service.dao")
@EnableTransactionManagement
public class MyBatisConfig implements EnvironmentAware {
    private Environment env;

    /**
     * 创建数据源
     *
     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
     */
    @Bean
    @Primary
    public DataSource getDataSource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", env.getProperty("jdbc.driverClassName"));
        props.put("url", env.getProperty("jdbc.url"));
        props.put("username", env.getProperty("jdbc.username"));
        props.put("password", env.getProperty("jdbc.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception {
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
    public PlatformTransactionManager transactionManager(DataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }
}
