package com.fzrj.starter.service;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.servlet.*;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

    /**
     * 添加过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new Filter() {
            private String PASSHOSTS;

            @Override
            public void init(FilterConfig filterConfig) throws ServletException {
                this.PASSHOSTS = filterConfig.getInitParameter("passIP");
            }

            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

            }

            @Override
            public void destroy() {

            }
        });
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.addInitParameter("passIP", "0:0:0:0:0:0:0:1|127.0.0.1");
        registrationBean.setName("intfVerifyFilter");
        registrationBean.setOrder(1);

        return registrationBean;
    }

}
