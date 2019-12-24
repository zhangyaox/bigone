<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>个人中心</title>
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
			<div class="col-md-9" style="background-color: red"><span>欢迎${sessionScope.user.username}登录</span>
				<a href="/passport/loginout">|注销</a>
			</div>
		</div>
		<hr style="height: 1px; border: none; border-top: 1px dotted;">
		<div class="row" style="height: 600px">
			<div class="col-md-3" style="background-color: blue;">
				<div class="list-group">
					<a href="#" class="list-group-item list-group-item-action active" data="/my/article/articles">
						我的文章</a> <a href="#"
						class="list-group-item list-group-item-action" data="/my/article/publish">发布文章</a> <a href="#"
						class="list-group-item list-group-item-action">我的收藏</a>
					<a href="#" class="list-group-item list-group-item-action">用户设置</a> <a href="#"
						class="list-group-item list-group-item-action disabled"
						tabindex="-1" aria-disabled="true">发布图片</a>
				</div>
			</div>
			<div class="col-md-9" id="center">
				<!-- 要引入富文本框 kindeditor -->
				<div style="display: none;"><!-- 隐藏起来 -->
					<jsp:include page="/resource/kindeditor/jsp/demo.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			//进入时就调用查找功能
			$("#center").load("/my/article/articles");
			
			
			//给 菜单的 a 标签添加点击事件
			$("a").click(function() {
				var url = $(this).attr("data");//取到我存入  data里面的路径
				//删除掉我之前添加的  点击时的 样式  就是颜色  $(this) 不可以是从当前对象开始 因为当前的对象是我现在选择的
				$("a").removeClass("active");
				//为当前的a标签 添加 我点击时的 样式  就是颜色
				$(this).addClass("active");
				$("#center").load(url);//load 是加载的方法  把load方法中路径查找到的 存入到 id是 center的div中
			})
		})
	</script>
</body>
</html>