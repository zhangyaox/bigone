<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<div class="container">
		<h1 align="center">${selectByPrimaryKey.title}</h1>
		<span><a href="/complain?id=${selectByPrimaryKey.id}">举报</a></span>
		<h3>${selectByPrimaryKey.user.username}<fmt:formatDate value="${selectByPrimaryKey.created}" pattern="yyyy-MM-dd HH:mm:ss"/></h3>
		${selectByPrimaryKey.content}
		
	</div>
</body>
</html>