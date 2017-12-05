#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

import ${package}.service.config.CorsConfig;
import ${package}.service.config.mq.RedisMqMapping;

/**
 * @className:${package}.service.Application
 * @description:springboot启动类
 * @version:v1.0.0
 * @date:2017年8月14日 上午10:59:40
 * @author:WangHao
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    // jar包启动方式
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        log.info("Springboot application started");
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            log.info("loaded bean's name: {}", beanName);
        }
        CorsConfig config = ctx.getBean(CorsConfig.class);
        log.info("cors allow mapping: {}", config.getAllowMapping());
        RedisMqMapping mapping = ctx.getBean(RedisMqMapping.class);
        log.info("Redis MQ mapping: {}", mapping.getMap());
    }

    // war包方式启动
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}
