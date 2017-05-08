package rabbitmqtest.publish;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import rabbitmqtest.connection.MqConnectionFactory;

/**
 * @className:rabbitmqtest.publish.ReceiveLogs
 * @description:测试发布订阅模式-接收日志类
 * @version:v1.0.0
 * @date:2016年12月6日 下午4:16:29
 * @author:WangHao
 */
public class ReceiveLogs
{
	private static final String EXCHANGE_NAME = "logs";

	public static void main(String[] argv) throws Exception
	{
		ConnectionFactory factory = MqConnectionFactory.getInstance();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		
		// 自动生成一个新的队列
		String queueName = channel.queueDeclare().getQueue();
		// 将队列绑定到交换机上
		channel.queueBind(queueName, EXCHANGE_NAME, "");

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channel)
		{
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException
			{
				String message = new String(body, "UTF-8");
				System.out.println(" [x] Received '" + message + "'");
			}
		};
		channel.basicConsume(queueName, true, consumer);
	}
}
