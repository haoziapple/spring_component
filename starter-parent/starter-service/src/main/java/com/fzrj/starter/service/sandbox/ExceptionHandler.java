package com.fzrj.starter.service.sandbox;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @className: com.fzrj.starter.service.sandbox.ExceptionHandler
 * @description: 实现HandlerExceptionResolver也可达到通用异常的处理效果(次序问题？)
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/11/28 16:28
**/
public class ExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        return null;
    }
}
