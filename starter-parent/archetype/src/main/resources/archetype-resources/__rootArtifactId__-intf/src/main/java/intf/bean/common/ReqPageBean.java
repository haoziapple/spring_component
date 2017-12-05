#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.intf.bean.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @className: ${package}.intf.bean.common.ReqPageBean
 * @description: 一般分页请求bean
 * @author: wanghao/haozixiaowang@163.com
 * @date: 2017/11/16 16:26
 **/
@ApiModel(value = "一般分页请求bean")
public class ReqPageBean<T> extends ReqBean {
    @ApiModelProperty("每页条数，默认20")
    private int pageSize = Page.DEFAULT_PAGE_SIZE;

    @ApiModelProperty("页码，默认1")
    private int currentPageNo = 1;

    public ReqPageBean() {
        super();
    }

    public ReqPageBean(T data) {
        super(data);
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public int getStartIndex() {
        return (currentPageNo - 1) * pageSize;
    }

    public int getEndIndex() {
        return currentPageNo * pageSize - 1;
    }

    @Override
    public String toString() {
        return "ReqPageBean{" +
                "pageSize=" + pageSize +
                ", currentPageNo=" + currentPageNo +
                "} " + super.toString();
    }
}
