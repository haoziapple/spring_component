package com.fzrj.starter.intf.bean.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @className: com.fzrj.starter.intf.bean.common.ResultBean
 * @description: 新版返回bean，controller统一使用，不允许传到service层
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/11/16 11:30
 **/
@ApiModel("通用返回bean")
public class ResultBean<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final int NO_LOGIN = -1;

    public static final int SUCCESS = 0;

    public static final int FAIL = 1;

    public static final int NO_PERMISSION = 2;

    @ApiModelProperty("返回信息")
    private String msg = "success";

    @ApiModelProperty("操作id")
    private String id;

    @ApiModelProperty("返回码")
    private int code = SUCCESS;

    @ApiModelProperty("异常栈信息")
    private String errTrace = "";

    @ApiModelProperty("包装返回数据")
    private T data;

    public ResultBean() {
        super();
    }

    public ResultBean(T data) {
        super();
        this.data = data;
    }

    public ResultBean(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = FAIL;
        this.errTrace = Arrays.asList(e.getStackTrace()).toString();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrTrace() {
        return errTrace;
    }

    public void setErrTrace(String errTrace) {
        this.errTrace = errTrace;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "msg='" + msg + '\'' +
                ", id='" + id + '\'' +
                ", code=" + code +
                ", errTrace='" + errTrace + '\'' +
                ", data=" + data +
                '}';
    }
}
