package rabbitmqtest.connection;

import com.rabbitmq.client.ConnectionFactory;

/**
 * @className:rabbitmqtest.connection.MqConnectionFactory
 * @description:rabbitmq连接工厂类
 * @version:v1.0.0
 * @date:2016年12月6日 下午2:26:08
 * @author:WangHao
 */
public class MqConnectionFactory
{
	private static ConnectionFactory factory = null;
	private static Object lock = new Object();

	public static ConnectionFactory getInstance()
	{
		if (factory == null)
		{
			synchronized (lock)
			{
				if (factory == null)
				{
					factory = new ConnectionFactory();
					factory.setHost("10.108.26.101");
					factory.setUsername("test");
					factory.setPassword("test");
				}
			}
		}
		return factory;
	}
}
