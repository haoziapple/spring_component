package rabbitmqtest.rpc;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import rabbitmqtest.connection.MqConnectionFactory;

public class RPCServer
{
	private static final String RPC_QUEUE_NAME = "rpc_queue";

	public static void main(String[] args) throws Exception
	{
		ConnectionFactory factory = MqConnectionFactory.getInstance();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		// 声明队列
		channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);

		// 限制：每次最多给一个消费者发送1条消息
		channel.basicQos(1);

		// 为rpc_queue队列创建消费者，用于处理请求
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(RPC_QUEUE_NAME, false, consumer);

		System.out.println(" [x] Awaiting RPC requests");

		while (true)
		{
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();

			// 获取请求中的correlationId属性值，并将其设置到结果消息的correlationId属性中
			BasicProperties props = delivery.getProperties();
			BasicProperties replyProps = new BasicProperties.Builder().correlationId(props.getCorrelationId()).build();
			// 获取回调队列名字
			String callQueueName = props.getReplyTo();

			String message = new String(delivery.getBody(), "UTF-8");

			System.out.println(" [.] fib(" + message + ")");
			System.out.println(fib(Integer.parseInt(message)));

			// 获取结果
			String response = "" + fib(Integer.parseInt(message));
			// 先发送回调结果
			channel.basicPublish("", callQueueName, replyProps, response.getBytes());
			// 后手动发送消息反馈
			channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
		}
	}

	/**
	 * 计算斐波列其数列的第n项
	 * 
	 * @param n
	 * @return
	 * @throws Exception
	 */
	private static int fib(int n) throws Exception
	{
		if (n < 0)
			throw new Exception("参数错误，n必须大于等于0");
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		return fib(n - 1) + fib(n - 2);
	}
}
