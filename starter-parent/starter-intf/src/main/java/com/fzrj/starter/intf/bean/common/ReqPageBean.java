package com.fzrj.starter.intf.bean.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @className: com.fzrj.starter.intf.bean.common.ReqPageBean
 * @description: 一般分页请求bean
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/11/16 16:26
**/
@ApiModel(value = "一般分页请求bean")
public class ReqPageBean<T> extends ReqBean{
    @ApiModelProperty("每页条数，默认20")
    private int pageSize = Page.DEFAULT_PAGE_SIZE;

    @ApiModelProperty("页码，默认1")
    private int pageNum = 1;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public String toString() {
        return "ReqPageBean{" +
                "pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                "} " + super.toString();
    }
}
