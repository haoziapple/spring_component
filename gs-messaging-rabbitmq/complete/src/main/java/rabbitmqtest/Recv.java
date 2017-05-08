package rabbitmqtest;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import rabbitmqtest.connection.MqConnectionFactory;

/**
 * @className:rabbitmqtest.Recv
 * @description:测试rabbitmq接受信息
 * @version:v1.0.0 
 * @date:2016年12月6日 下午2:20:49
 * @author:WangHao
 */
public class Recv
{
	private final static String QUEUE_NAME = "hello";

	public static void main(String[] argv) throws java.io.IOException, java.lang.InterruptedException, TimeoutException
	{

		ConnectionFactory factory = MqConnectionFactory.getInstance();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
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
		channel.basicConsume(QUEUE_NAME, true, consumer);
		Thread.sleep(10000);
		channel.close();
		connection.close();
	}

}
