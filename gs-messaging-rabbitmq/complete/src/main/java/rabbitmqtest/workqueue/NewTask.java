package rabbitmqtest.workqueue;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import rabbitmqtest.connection.MqConnectionFactory;

/**
 * @className:rabbitmqtest.workqueue.NewTask
 * @description:测试任务分发生产者
 * @version:v1.0.0
 * @date:2016年12月6日 下午2:22:44
 * @author:WangHao
 */
public class NewTask
{
	public static void main(String[] args) throws IOException, TimeoutException
	{
		Connection connection = MqConnectionFactory.getInstance().newConnection();
		Channel channel = connection.createChannel();

		String message = "MQ消息最大量测试";
		// 将消息设为持久化
		// 声明一个队列，并且在队列里发布消息
		for (int i = 0; i < 1000000; i++)
		{
			channel.basicPublish("schedule_exchange_dev", "haozi_demo", MessageProperties.PERSISTENT_TEXT_PLAIN,
					(message).getBytes());
			System.out.println(" [x] Sent '" + message + "'");
		}

		// 关闭通道与连接
		channel.close();
		connection.close();
	}
}
