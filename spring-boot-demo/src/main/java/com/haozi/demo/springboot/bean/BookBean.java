package com.haozi.demo.springboot.bean;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton") // 默认配置，容器中只有一个实例
// @Scope("Prototype") //每次调用新建一个实例
@ConfigurationProperties(prefix = "book", locations = "classpath:book.properties")
public class BookBean implements Serializable
{
	/**
	*
	*/
	private static final long serialVersionUID = 1L;
	private String name;
	private String author;
	private String price;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getPrice()
	{
		return price;
	}

	public void setPrice(String price)
	{
		this.price = price;
	}

	@Override
	public String toString()
	{
		return "BookBean [name=" + name + ", author=" + author + ", price=" + price + "]";
	}
}
