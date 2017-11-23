package com.fzrj.starter.service.common.filter;

import com.fzrj.starter.service.common.util.IPUtil;
import com.fzrj.starter.service.common.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className: com.fzrj.starter.service.common.filter.RateLimitFilter
 * @description: 访问频次限制过滤器
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/11/23 10:35
 **/
public class RateLimitFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(RateLimitFilter.class);

    private RedisUtil redisUtil;

    public RateLimitFilter() {
    }

    public RateLimitFilter(ApplicationContext context) {
        this.redisUtil = context.getBean(RedisUtil.class);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRsp = (HttpServletResponse) response;
        String ip = IPUtil.getIpAddr(httpReq);
        String mapping = httpReq.getRequestURI();
        String key = ip + RedisUtil.KEY_SPLIT + mapping;
        logger.info("{} is requesting {}", ip, mapping);
        if (redisUtil.hasKey(key)) {
            httpRsp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpRsp.setHeader("errorMsg", "cannot request over 1/s");
            return;
        } else {
            redisUtil.set(key, "1", 1L);
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
