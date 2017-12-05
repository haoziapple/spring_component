#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.sandbox;

/**
 * 内部枚举使用类，值需要为String类型，并且枚举名称需要与值完全一致。
 */
public interface IEnum {
    String getValue();
}
