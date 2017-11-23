package com.fzrj.starter.service.config;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.fzrj.starter.service.common.filter.RateLimitFilter;
import com.fzrj.starter.service.common.filter.UserFilter;
import com.fzrj.starter.service.common.util.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fzrj.starter.service.common.util.IPUtil;

/**
 * @className:com.fzrj.starter.service.config.FilterConfig
 * @description:自定义过滤器
 * @version:v1.0.0
 * @date:2017年8月14日 上午10:27:52
 * @author:WangHao
 */
@Configuration
public class FilterGlobalConfig {
    private static final Logger log = LoggerFactory.getLogger(FilterGlobalConfig.class);

    @Autowired
    private SpringContextUtil springContextUtil;

    /**
     * 添加过滤器(样例)
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.addInitParameter("passIP", "0:0:0:0:0:0:0:1|127.0.0.1");
        registrationBean.setName("intfVerifyFilter");
        registrationBean.setOrder(1);
        registrationBean.setFilter(new Filter() {
            private String[] passHosts;

            @Override
            public void init(FilterConfig filterConfig) throws ServletException {
                String param = filterConfig.getInitParameter("passIP");
                if (param != null)
                    this.passHosts = param.split("\\|");
                log.info("初始化过滤器IP白名单: {}", Arrays.toString(passHosts));
            }

            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                                 FilterChain filterChain) throws IOException, ServletException {
                log.info("do filter");
                if (Arrays.asList(passHosts).contains(IPUtil.getIpAddr((HttpServletRequest) servletRequest)))
                    filterChain.doFilter(servletRequest, servletResponse);
            }

            @Override
            public void destroy() {
                log.info("destroy");
            }
        });

        return registrationBean;
    }

    /**
     * @Description: 用户过滤器，将请求中的tokenId转化为用户信息
     * @param:
     * @author: wanghao/haozixiaowang@163.com
     * @date: 2017/11/23 10:27
     **/
    @Bean
    public FilterRegistrationBean userFilterBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("userFilter");
        registrationBean.setOrder(2);

        registrationBean.setFilter(new UserFilter());
        return registrationBean;
    }

    /**
     * @Description: 访问频次限制过滤器
     * @param:
     * @author: wanghao/haozixiaowang@163.com
     * @date: 2017/11/23 10:52
     **/
    @Bean
    public FilterRegistrationBean rateLimitFilterBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("rateLimitFilter");
        registrationBean.setOrder(3);

        registrationBean.setFilter(new RateLimitFilter(springContextUtil.getContext()));
        return registrationBean;
    }

}
