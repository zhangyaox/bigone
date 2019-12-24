<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理员页面</title>
<link href="/resource/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-1.8.2.min.js"></script>
</head>
<body>
	<div class="container">
		<!--container  是12 列  -->
		<div class="row" style="height: 60px; margin-top: 20px">
			<div class="col-md-3" style="background-color: green;">
				<img alt="错误" src="/resource/images/119781951875d59979o.jpg"
					style="height: 50px; width: 50px" class="rounded-circle">
			</div>
			<div class="col-md-9" style="background-color: red">
			<span>欢迎${sessionScope.admin.username}登录</span>
				<a href="/passport/loginout">|注销</a>
			</div>
		</div>
		<hr style="height: 1px; border: none; border-top: 1px dotted;">
		<div class="row" style="height: 600px">
			<div class="col-md-3" style="background-color: blue;">
				<!-- As a link -->
				<div style="margin-top: 20px">
					<nav class="navbar navbar-light bg-light">
						<a class="navbar-brand" href="#" data="/admin/user/selects">用户管理</a>
					</nav>
					<!-- As a heading -->
					<nav class="navbar navbar-light bg-light">
						<span class="navbar-brand mb-0 h1">
							<a class="navbar-brand" href="#" data="/admin/article/selects">文章管理</a>
						</span>
					</nav>
					
					<nav class="navbar navbar-light bg-light">
						<span class="navbar-brand mb-0 h1">
							<a class="navbar-brand" href="#" data="/admin/article/selectComplain">投诉管理</a>
						</span>
					</nav>
				</div>
			</div>
			<div class="col-md-9" id="center"></div>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			//给 菜单的 a 标签添加点击事件
			$("a").click(function(){
				var url=$(this).attr("data");//取到我存入  data里面的路径
				//删除掉我之前添加的  点击时的 样式  就是颜色  $(this) 不可以是从当前对象开始 因为当前的对象是我现在选择的
				$("a").removeClass("list-group-item-success");
				//为当前的a标签 添加 我点击时的 样式  就是颜色
				$(this).addClass("list-group-item-success");
				$("#center").load(url);//load 是加载的方法  把load方法中路径查找到的 存入到 id是 center的div中
			})
		})
	</script>
</body>
</html>