<!DOCTYPE html>
<html>
	<#-- 使用注释 -->
	<head lang="en">
		<meta charset="UTF-8" />
		<title></title>
	</head>
	<body>
		FreeMarker模板引擎
		<h1>测试取值:${host}</h1>

		<#-- 读取map里数据 -->
		<p>读取map里数据:${data.k1}</p>

		<#-- 循环list -->
		<ul>
			<li>循环list</li>
			<#list list as being>
			<li>
				${being.name} for ${being.age} Euros
			</li>
			</#list>
			<p>${list[1].name}</p>
		</ul>

		<#-- 测试条件判断 -->
		<p>测试条件判断</p>
		<#if data.k3 gt 2>
		<p>data.k1 gt data.k2</p>
		</#if>

		<#-- 测试include功能 -->
		<#include "/module/common.html">
		
		<#-- 测试判空 -->
		<h1>测试判空 ${user!"visitor"}!</h1>
		<#if user??><h1>Welcome ${user}!</h1></#if>
		
		<#-- 测试xml特殊字符 -->
		<h1>测试xml特殊字符:${name}</h1>
	</body>
</html>