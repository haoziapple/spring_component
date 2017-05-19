#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.intf;

/**
 * @className:${package}.intf.UserIntf
 * @description:用户接口
 * @version:v${version}
 * @date:2017年5月17日 下午4:56:23
 * @author:WangHao
 */
public interface UserIntf
{
	// 校验用户
	String checkUser(String userId);
}
