<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${selectByPrimaryKey.title}</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="/resource/css/bootstrap.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<h1 align="center">${selectByPrimaryKey.title}</h1>
		<h3>${selectByPrimaryKey.user.username},<fmt:formatDate value="${selectByPrimaryKey.created}" pattern="yyyy-MM-dd HH:mm:ss"/></h3>
		${selectByPrimaryKey.content}
		
	</div>
	
</body>
</html>