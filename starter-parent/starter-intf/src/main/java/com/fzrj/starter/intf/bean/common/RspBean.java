package com.fzrj.starter.intf.bean.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @className:com.fzrj.starter.intf.bean.common.RspBean
 * @description:一般返回bean
 * @version:v1.0.0
 * @date:2017年5月17日 下午1:27:54
 * @author:WangHao
 */
@ApiModel("一般返回bean")
public class RspBean
{
	@ApiModelProperty("操作id")
	private String id;
	@ApiModelProperty("返回码")
	private String rspCode = "ok";
	@ApiModelProperty("返回信息")
	private String rspMsg = "ok";

	private String data;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getRspCode()
	{
		return rspCode;
	}

	public void setRspCode(String rspCode)
	{
		this.rspCode = rspCode;
	}

	public String getRspMsg()
	{
		return rspMsg;
	}

	public void setRspMsg(String rspMsg)
	{
		this.rspMsg = rspMsg;
	}

	public String getData()
	{
		return data;
	}

	public void setData(String data)
	{
		this.data = data;
	}

	@Override
	public String toString()
	{
		return "RspBean [id=" + id + ", rspCode=" + rspCode + ", rspMsg=" + rspMsg + ", data=" + data + "]";
	}
}
