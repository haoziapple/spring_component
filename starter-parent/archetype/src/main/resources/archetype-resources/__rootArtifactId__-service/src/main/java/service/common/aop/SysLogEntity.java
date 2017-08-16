#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.common.aop;

import java.io.Serializable;
import java.util.Date;

public class SysLogEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	// 用户操作
	private String operation;
	// 请求方法
	private String method;
	// 请求参数
	private String params;
	// 返回结果
	private String rsp;
	// IP地址
	private String ip;
	// 创建时间
	private Date createDate;
	private Long time;

	public String getRsp()
	{
		return rsp;
	}

	public void setRsp(String rsp)
	{
		this.rsp = rsp;
	}

	/**
	 * 设置：用户操作
	 */
	public void setOperation(String operation)
	{
		this.operation = operation;
	}

	/**
	 * 获取：用户操作
	 */
	public String getOperation()
	{
		return operation;
	}

	/**
	 * 设置：请求方法
	 */
	public void setMethod(String method)
	{
		this.method = method;
	}

	/**
	 * 获取：请求方法
	 */
	public String getMethod()
	{
		return method;
	}

	/**
	 * 设置：请求参数
	 */
	public void setParams(String params)
	{
		this.params = params;
	}

	/**
	 * 获取：请求参数
	 */
	public String getParams()
	{
		return params;
	}

	/**
	 * 设置：IP地址
	 */
	public void setIp(String ip)
	{
		this.ip = ip;
	}

	/**
	 * 获取：IP地址
	 */
	public String getIp()
	{
		return ip;
	}

	/**
	 * 设置：创建时间
	 */
	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	/**
	 * 获取：创建时间
	 */
	public Date getCreateDate()
	{
		return createDate;
	}

	public Long getTime()
	{
		return time;
	}

	public void setTime(Long time)
	{
		this.time = time;
	}

	@Override
	public String toString()
	{
		return "SysLogEntity [operation=" + operation + ", method=" + method + ", params=" + params + ", rsp=" + rsp
				+ ", ip=" + ip + ", createDate=" + createDate + ", time=" + time + "]";
	}
}
