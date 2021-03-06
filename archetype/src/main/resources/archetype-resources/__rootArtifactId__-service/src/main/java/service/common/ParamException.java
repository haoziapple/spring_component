#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.common;

/**
 * @className:${package}.service.common.ParamException
 * @description:自定义参数非法异常
 * @version:v${version}
 * @date:2017年5月18日 下午1:06:10
 * @author:WangHao
 */
public class ParamException extends IllegalArgumentException
{
	private static final long serialVersionUID = 1L;

	public ParamException(String message)
	{
		super(message);
	}
}
