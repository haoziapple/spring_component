#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @className: ${package}.service.common.filter.UserFilter
 * @description: 用户信息过滤器
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/11/17 11:27
 **/
public class UserFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(UserFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String tokenId = ((HttpServletRequest) request).getHeader("tokenId");
        logger.info("Request with tokenId: {}", tokenId);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
