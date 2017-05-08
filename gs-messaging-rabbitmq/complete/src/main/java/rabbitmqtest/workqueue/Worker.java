package rabbitmqtest.workqueue;

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
 * @className:rabbitmqtest.workqueue.Worker
 * @description:测试任务分发消费者
 * @version:v1.0.0
 * @date:2016年12月6日 下午2:24:01
 * @author:WangHao
 */
public class Worker
{
	// 队列名称
	private final static String QUEUE_NAME = "hello";

	private static void doWork(String task) throws InterruptedException
	{
		for (char ch : task.toCharArray())
		{
			if (ch == '.')
				Thread.sleep(1000);
		}
	}

	public static void main(String[] argv) throws java.io.IOException, java.lang.InterruptedException, TimeoutException
	{

		ConnectionFactory factory = MqConnectionFactory.getInstance();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		// 同时只会接收一条未处理的消息
		// 消息未处理完之前，服务器不会再向该消费者投递消息
		channel.basicQos(1);

		// 声明队列是持久化的(如果之前该队列已被声明为非持久的会报错)
		boolean durable = true;
		channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channel)
		{
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException
			{
				String message = new String(body, "UTF-8");
				System.out.println(" [x] Received '" + message + "'");

				try
				{
					doWork(message);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				finally
				{
					System.out.println(" [x] Done");
					channel.basicAck(envelope.getDeliveryTag(), false);
				}
			}
		};
		
		// true时，服务器只要发送完消息则认为消息已经处理
		boolean autoAck = false; // acknowledgment is covered below
		channel.basicConsume(QUEUE_NAME, autoAck, consumer);
	}
}
