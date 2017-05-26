package com.haozi.spring.simple.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @className:com.haozi.spring.simple.controller.Hello2Controller
 * @description:使用注解Controller
 * @version:v1.0.0
 * @date:2017年5月26日 下午3:58:39
 * @author:WangHao
 */
@Controller
@RequestMapping("/c")
public class Hello2Controller
{
	@Autowired
	private Bean bean;
	// @Controller：用于标识是处理器类；
	// @RequestMapping：请求到处理器功能方法的映射规则；
	// @RequestParam：请求参数到处理器功能处理方法的方法参数上的绑定；
	// @ModelAttribute：请求参数到命令对象的绑定；
	// @SessionAttributes：用于声明session级别存储的属性，放置在处理器类上，通常列出模型属性（如@ModelAttribute）对应的名称，则这些属性会透明的保存到session中；
	// @InitBinder：自定义数据绑定注册支持，用于将请求参数转换到命令对象属性的对应类型

	// @CookieValue：cookie数据到处理器功能处理方法的方法参数上的绑定；
	// @RequestHeader：请求头（header）数据到处理器功能处理方法的方法参数上的绑定；
	// @RequestBody：请求的body体的绑定（通过HttpMessageConverter进行类型转换）；
	// @ResponseBody：处理器功能处理方法的返回值作为响应体（通过HttpMessageConverter进行类型转换）；
	// @ResponseStatus：定义处理器功能处理方法/异常处理器返回的状态码和原因；
	// @ExceptionHandler：注解式声明异常处理器；
	// @PathVariable：请求URI中的模板变量部分到处理器功能处理方法的方法参数上的绑定，从而支持RESTful架构风

	@RequestMapping("/hello2")
	public ModelAndView hello2(HttpServletRequest req, HttpServletResponse rsp)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("wang", 2);
		mv.setViewName("hello2");
		return mv;
	}

	@RequestMapping("/test")
	public void test(@RequestParam(value = "t", defaultValue = "a", required = false) String s)
	{
		System.out.println(s);
		System.out.println(bean);
	}
}
