package rabbitmqtest.publish;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import rabbitmqtest.Message;
import rabbitmqtest.connection.MqConnectionFactory;

/**
 * @className:rabbitmqtest.publish.EmitLog
 * @description:测试发布订阅模式：发送日志类
 * @version:v1.0.0
 * @date:2016年12月6日 下午4:15:33
 * @author:WangHao
 */
public class EmitLog
{
	// 交换机名称
	private static final String EXCHANGE_NAME = "logs";

	public static void main(String[] argv) throws java.io.IOException, TimeoutException
	{

		ConnectionFactory factory = MqConnectionFactory.getInstance();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		// 声明交换机类型为fanout
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

		String message = Message.getMessage(argv);

		// 发布消息时，如果没有队列绑定到交换机消息会丢失
		channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");

		channel.close();
		connection.close();
	}
}
