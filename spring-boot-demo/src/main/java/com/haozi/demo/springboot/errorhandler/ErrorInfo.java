package com.haozi.demo.springboot.errorhandler;

/**
 * @className:com.haozi.demo.springboot.errorhandler.ErrorInfo
 * @description:返回错误实体类
 * @version:v1.0.0
 * @date:2017年5月15日 下午4:15:12
 * @author:WangHao
 */
public class ErrorInfo<T>
{
	public static final Integer OK = 0;
	public static final Integer ERROR = 100;
	private Integer code;
	private String message;
	private String url;
	private T data;
	public Integer getCode()
	{
		return code;
	}
	public void setCode(Integer code)
	{
		this.code = code;
	}
	public String getMessage()
	{
		return message;
	}
	public void setMessage(String message)
	{
		this.message = message;
	}
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public T getData()
	{
		return data;
	}
	public void setData(T data)
	{
		this.data = data;
	}
	public static Integer getOk()
	{
		return OK;
	}
	public static Integer getError()
	{
		return ERROR;
	}
	@Override
	public String toString()
	{
		return "ErrorInfo [code=" + code + ", message=" + message + ", url=" + url + ", data=" + data + "]";
	}
}
