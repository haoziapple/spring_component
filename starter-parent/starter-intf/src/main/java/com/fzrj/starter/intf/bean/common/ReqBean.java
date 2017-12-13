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
    public ReqBean() {
        super();
    }

    public ReqBean(T data) {
        super();
        this.data = data;
    }
    
    @ApiModelProperty("操作id")
    @NotBlank(message="操作id不可以为空")
    protected String id;

    @ApiModelProperty(value = "包装数据")
    @Valid
    protected T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	@Override
    public String toString() {
        return "ReqBean{" +
        		"id='" + id + '\'' +
                ", data=" + data +
                '}';
    }
}
