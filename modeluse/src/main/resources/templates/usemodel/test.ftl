<!DOCTYPE html>
<html>
	<#-- 使用注释 -->
	<head lang="en">
		<meta charset="UTF-8" />
		<title></title>
	</head>
	<body>
		FreeMarker模板引擎--测试model
		<h1>测试取值:${attributeName}</h1>
		<h1>测试具体类取值:${personBean.name}</h1>
		<h1>测试对象合并:${personBean.age}+${personBean.name}</h1>
		<h1>测试对象合并:${p.age}+${p.name}</h1>
		<h1>测试对象合并:${p}</h1>
	</body>
</html>