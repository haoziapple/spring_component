package com.fzrj.starter.service.common;

import com.fzrj.starter.intf.bean.common.ResultBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @className:com.fzrj.starter.service.common.ErrorHandler
 * @description:公共的异常处理类，Controller抛出的异常在这里处理，统一返回错误信息
 * @version:v1.0.0
 * @date:2017年5月18日 上午9:06:17
 * @author:WangHao
 */
@ControllerAdvice
public class ErrorHandler {
    // 一般异常处理
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBean defaultErrorHandler(HttpServletRequest req, Exception e) {
        return new ResultBean(e);
    }

    // 接口参数异常处理
    @ExceptionHandler(value = ParamException.class)
    @ResponseBody
    public ResultBean paramErrorHandler(HttpServletRequest req, ParamException e) {
        return new ResultBean(e);
    }
}
