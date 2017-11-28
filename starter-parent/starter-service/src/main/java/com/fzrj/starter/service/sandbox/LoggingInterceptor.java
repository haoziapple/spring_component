package com.fzrj.starter.service.sandbox;

/**
 * 服务war从request中还原logcontext，服务不使用session，直接保存在MDC中
 */

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @className: com.fzrj.starter.service.sandbox.LoggingInterceptor
 * @description: TODO
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/11/28 16:30
**/
public class LoggingInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LogContext logContext = LogUtils.buildLogContext(request);
        if (logContext != null) {
            LogUtils.saveLogContext(logContext);
        }
        return super.preHandle(request, response, handler);
    }
}
