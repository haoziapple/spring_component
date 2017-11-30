package com.fzrj.starter.intf.bean.common;

/**
 * @className: com.aquatic.schedule.web.bussiness.common.ResultPageBean
 * @description: 一般返回分页bean
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/11/30 15:02
 **/
public class ResultPageBean<T> extends ResultBean {
    private int count;

    public ResultPageBean() {
        super();
    }

    public ResultPageBean(T data) {
        super();
        super.setData(data);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ResultPageBean{" +
                "count=" + count +
                "} " + super.toString();
    }
}
