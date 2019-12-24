<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="/resource/css/bootstrap.css" rel="stylesheet">
<link href="/resource/css/jquery/screen.css" rel="stylesheet"><!-- 验证输出加颜色 -->
<script type="text/javascript" src="/resource/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="/resource/js/jquery.validate.js"></script>
</head>
<body>
	<div class="container">
	<div class="row">
		<div class="col-md-6" style="width: 350px">
			<form id="form1" action="/passport/reg" method="post">
				<h1>用户注册</h1>
				<hr>
				<span style="color: red">${error}</span>
				<div class="form-group">
					<label class="username">用户名</label> <input id="username"
						class="form-control" value="${user.username}" type="text" name="username">
				</div>
				<div class="form-group">
					<label class="password">密码</label> <input id="password"
						class="form-control" value="${user.password}" type="password" name="password">
				</div>
				<div class="form-group">
					<label class="repassword">确认密码</label> <input id="repassword"
						class="form-control" value="${user.repassword}" type="password" name="repassword">
				</div>
				<div class="form-group form-inline">
					<label class="gender">性别</label> <input 
						class="form-control" checked="checked" type="radio" name="gender"
						value="0">男 <input class="form-control"
						type="radio" name="gender" value="1">女
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-info">注册</button>
					<!-- submit  表单提交 -->
					<button type="button" class="btn btn-warning">重置</button>
				</div>
			</form>
		</div>
		<div class="col-md-6">
			<img alt="" src="/resource/images/pic.jpg" style="width: 350px" class="rounded-circle">
		</div>
		</div>
		
	</div>
	
	<script type="text/javascript">
		$(function(){
			$("#form1s").validate({
				//定义校验规则
				rules:{
					username:{
						required:true,
					},
					password:{
						required:true,
					},
					repassword:{
						required:true,
					}
				},
				//提示信息
				messages:{
					username:{
						required:"非空",
					},
					password:{
						required:"密码非空",
					},
					repassword:{
						required:"密码非空",
					}
				}
			});
		})
	</script>
</body>
</html>