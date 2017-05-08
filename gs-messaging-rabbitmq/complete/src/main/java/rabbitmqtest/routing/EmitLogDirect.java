package rabbitmqtest.routing;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import rabbitmqtest.connection.MqConnectionFactory;

/**
 * @className:rabbitmqtest.routing.EmitLogDirect
 * @description:测试Direct类型的交换机--发送类
 * @version:v1.0.0
 * @date:2016年12月6日 下午5:13:02
 * @author:WangHao
 */
public class EmitLogDirect
{
	private static final String EXCHANGE_NAME = "direct_logs";

	public static void main(String[] argv) throws java.io.IOException, TimeoutException
	{

		ConnectionFactory factory = MqConnectionFactory.getInstance();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(EXCHANGE_NAME, "direct");

		// 设定路由键为black
		String severity = "black";
		String message = "Hello World Wanghao!";
		
		channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
		System.out.println(" [x] Sent '" + severity + "':'" + message + "'");

		channel.close();
		connection.close();
	}

}
