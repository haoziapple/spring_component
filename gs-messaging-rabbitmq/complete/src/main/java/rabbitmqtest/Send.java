package rabbitmqtest;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import rabbitmqtest.connection.MqConnectionFactory;

/**
 * @className:rabbitmqtest.Send
 * @description:测试rabbitmq发送信息
 * @version:v1.0.0
 * @date:2016年12月6日 下午2:20:04
 * @author:WangHao
 */
public class Send
{
	private final static String QUEUE_NAME = "schedule_queue_test";

	public static void main(String[] argv) throws java.io.IOException, TimeoutException
	{
		ConnectionFactory factory = MqConnectionFactory.getInstance();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		// 声明一个队列，并且在队列里发布消息
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		String message = "Hello World Wanghao3!";
		channel.basicPublish("schedule_exchange_test", "schedule", null, message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");

		// 关闭通道与连接
		channel.close();
		connection.close();
	}
}
