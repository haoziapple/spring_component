package rabbitmqtest.rpc;

import com.rabbitmq.client.AMQP.BasicProperties;

import rabbitmqtest.connection.MqConnectionFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class RPCClient
{
	private static final String RPC_QUEUE_NAME = "rpc_queue";

	private Connection connection;
	private Channel channel;
	private String replyQueueName;
	private QueueingConsumer consumer;

	public RPCClient() throws Exception
	{
		ConnectionFactory factory = MqConnectionFactory.getInstance();
		// 创建一个连接
		connection = factory.newConnection();
		// 创建一个频道
		channel = connection.createChannel();

		// 声明队列
		channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);

		// 为每一个客户端获取一个随机的回调队列
		replyQueueName = channel.queueDeclare().getQueue();
		// 为每一个客户端创建一个消费者（用于监听回调队列，获取结果）
		consumer = new QueueingConsumer(channel);
		// 消费者与队列关联
		channel.basicConsume(replyQueueName, true, consumer);
	}

	/**
	 * 获取斐波列其数列的值
	 * 
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public String call(String message) throws Exception
	{
		String response = null;
		String corrId = java.util.UUID.randomUUID().toString();

		// 设置replyTo和correlationId属性值
		BasicProperties props = new BasicProperties.Builder().correlationId(corrId).replyTo(replyQueueName).build();

		// 发送消息到rpc_queue队列
		channel.basicPublish("", RPC_QUEUE_NAME, props, message.getBytes());

		while (true)
		{
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			if (delivery.getProperties().getCorrelationId().equals(corrId))
			{
				response = new String(delivery.getBody(), "UTF-8");
				break;
			}
		}
		channel.close();
		channel.getConnection().close();

		return response;
	}

	public static void main(String[] args) throws Exception
	{
		RPCClient fibonacciRpc = new RPCClient();
		String result = fibonacciRpc.call("11");
		System.out.println("fib(11) is " + result);
	}
}
