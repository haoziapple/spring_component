package com.haozi.spring.simple;

import java.util.ArrayList;
import java.util.List;

public class App
{
	public static void main(String[] args)
	{
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		L: for (String key1 : list)
		{
			System.out.println("外层循环L:" + key1);
			for (String key2 : list)
			{
				System.out.println("内层循环:" + key2);
				if ("2".equals(key2))
					// break L;
					continue L;
			}
		}
	}
}
