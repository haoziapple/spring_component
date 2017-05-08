package rabbitmqtest.routing;

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
 * @className:rabbitmqtest.routing.ReceiveLogsDirect
 * @description:测试Direct类型的交换机--接收类
 * @version:v1.0.0
 * @date:2016年12月6日 下午5:12:13
 * @author:WangHao
 */
public class ReceiveLogsDirect
{
	private static final String EXCHANGE_NAME = "direct_logs";

	public static void main(String[] argv) throws Exception
	{
		ConnectionFactory factory = MqConnectionFactory.getInstance();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		// 声明类型为direct的交换机
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");
		// 自动创建新的队列
		String queueName = channel.queueDeclare().getQueue();

		if (argv.length < 1)
		{
			System.err.println("Usage: ReceiveLogsDirect [info] [warning] [error]");
			System.exit(1);
		}

		for (String severity : argv)
		{
			// 将多个路由键绑定到交换机上
			channel.queueBind(queueName, EXCHANGE_NAME, severity);
		}
		
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channel)
		{
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException
			{
				String message = new String(body, "UTF-8");
				System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
			}
		};
		channel.basicConsume(queueName, true, consumer);
	}
}
