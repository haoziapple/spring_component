package rabbitmqtest;

/**
 * @className:rabbitmqtest.Message
 * @description:消息类
 * @version:v1.0.0
 * @date:2016年12月6日 下午4:27:36
 * @author:WangHao
 */
public class Message
{
	public static String getMessage(String[] strings)
	{
		if (strings.length < 1)
			return "Hello World!";
		return joinStrings(strings, " ");
	}

	public static String joinStrings(String[] strings, String delimiter)
	{
		int length = strings.length;
		if (length == 0)
			return "";
		StringBuilder words = new StringBuilder(strings[0]);
		for (int i = 1; i < length; i++)
		{
			words.append(delimiter).append(strings[i]);
		}
		return words.toString();
	}
}
