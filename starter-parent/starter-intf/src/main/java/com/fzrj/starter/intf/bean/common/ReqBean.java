package com.fzrj.starter.intf.bean.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;

/**
 * @className:com.fzrj.starter.intf.bean.common.ReqBean
 * @description:一般请求bean
 * @version:v1.0.0
 * @date:2017年5月17日 下午1:12:15
 * @author:WangHao
 */
@ApiModel(value = "一般请求bean")
public class ReqBean<T> {
    @ApiModelProperty(value = "请求id")
    @NotBlank(message = "id不可以为空")
    private String id;

    @ApiModelProperty(value = "请求token")
    private String token;

    @ApiModelProperty(value = "包装数据")
    @Valid
    private T data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ReqBean{" +
                "id='" + id + '\'' +
                ", token='" + token + '\'' +
                ", data=" + data +
                '}';
    }
}
