<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form method="post">
	username:<input type="text" name="username" value="${user.username}"><br />
	password:<input type="password" name="username"><br /> 
	city:<select>
		<c:forEach items="${cityList}" var="city">
			<option>${city}</option>
		</c:forEach>
	</select><br /> <input type="submit" value="注册" />
</form>
