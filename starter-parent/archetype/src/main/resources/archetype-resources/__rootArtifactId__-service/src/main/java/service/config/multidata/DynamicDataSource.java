#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.config.multidata;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @className:${package}.service.config.multidata.DynamicDataSource
 * @description:动态数据源
 * @version:v1.0.0
 * @date:2017年8月14日 下午4:05:01
 * @author:WangHao
 */
public class DynamicDataSource extends AbstractRoutingDataSource
{
	@Override
	protected Object determineCurrentLookupKey()
	{
		return DataSourceSwitcher.getDbKey();
	}
}
