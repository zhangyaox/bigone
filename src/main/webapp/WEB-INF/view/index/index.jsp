<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>首页</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="/resource/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
<!-- <style type="text/css">
	.channel-item:visited {
    color: #444;
}
a:visited {
    color: #999;
}
.channel-item {
    display: block;
    width: 110px;
    height: 40px;
    line-height: 40px;
    text-align: center;
    color: #444;
    border-radius: 4px;
    margin-bottom: 2px;
    transition-property: color,background-color;
}

</style> -->
</head>
<body>
	<div class="container-fluid">
		<div class="row" style="height: 34px;background-color: #222222">
			<a href="#"><font color="#ffffff" size="3px">下载APP</font></a>
			<span style="left: -88"><a><font color="#ffffff"><a href="/passport/login">登录</a>|<a href="/passport/reglogin">注册</a></font></a></span>
		</div>
	</div>
	<div class="container" style="margin-top: 5px">
		<div class="row">
			<div class="col-md-2" style="height: 550px;">
			<img alt="" src="/resource/images/log.png" style="height: 80px;width: 170px">
				<ul class="list-group">
					<a href="/?hot=1" class="channel-item"><li class="list-group-item">推荐</li></a>
					<c:forEach items="${selectsChannel}" var="li">
						<a href="/?channelId=${li.id}" class="channel-item"><li class="list-group-item">${li.name}</li></a>
						<!-- active  是激活的意思 -->
					</c:forEach>
				</ul>
			</div>

			

			<div class="col-md-7" style="">
			
				<form action="selectEs" method="get">${date }
					<div class="input-group mb-3">
						<input type="text" name="title" class="form-control"
							placeholder="输入查找内容"
							aria-label="输入查找内容"
							aria-describedby="button-addon2">
						<div class="input-group-append">
							<button class="btn btn-outline-secondary"
								id="button-addon2">查找</button>
						</div>
					</div>
				</form>
			
				<!-- 创建一个div 放 分类    中间的-->
				<!-- 轮播图 -->
				<div id="carouselExampleCaptions" class="carousel slide"
					data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#carouselExampleCaptions" data-slide-to="0"
							class="active"></li>
						<li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
						<li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
					</ol>

					<div class="carousel-inner">
						<c:forEach items="${selects}" var="l" varStatus="i">
							
							<div class="carousel-item ${i.index==0?"active":""}">
								<img src="/pic/${l.url}" class="d-block w-100" alt="..." style="height: 300px;width: 400px">
								<div class="carousel-caption d-none d-md-block">
									<h5>${l.title }</h5>
									
								</div>
							</div>

						</c:forEach>
					</div>

				</div>
					<a class="carousel-control-prev" href="#carouselExampleCaptions"
						role="button" data-slide="prev"> <span
						class="carousel-control-prev-icon" aria-hidden="true"></span> <span
						class="sr-only">Previous</span>
					</a> <a class="carousel-control-next" href="#carouselExampleCaptions"
						role="button" data-slide="next"> <span
						class="carousel-control-next-icon" aria-hidden="true"></span> <span
						class="sr-only">Next</span>
					</a>
				
				
				<div>
					<nav class="navbar navbar-expand-lg navbar-light bg-light">
						<div class="collapse navbar-collapse" id="navbarSupportedContent">
							<ul class="navbar-nav mr-auto">
							<!-- 加一个全部的标签 -->
							<li class="nav-item active"><a class="nav-link" href="/?channelId=${article.channelId}"><!-- 全部只要有一个菜单的就好了 -->
											全部
									</a></li>
								<c:forEach items="${selectsCategory}" var="l">
									<li class="nav-item active"><a class="nav-link" href="/?categoryId=${l.id}&channelId=${article.channelId}"><!--  -->
											${l.name}
									</a></li>
								</c:forEach>
							</ul>
						</div>
					</nav>
				</div>
				<!-- 分类的文章 显示在这里 -->
				<div>
					<c:forEach items="${info.list }" var="l">
						<div class="media">
							<img src="/pic/${l.picture}" class="mr-3" alt="..." style="width: 156; height: 101;">
							<div class="media-body">
								<h5 class="mt-0"><a href="article?id=${l.id}" target="_blank">${l.title}</a></h5>
									<!-- 发布时间 -->
								<h5 class="mt-0">${l.user.username} <fmt:formatDate value="${l.created}" pattern="yyyy-MM-dd HH:mm:ss"/></h5>
							</div>
						</div><hr>
					</c:forEach>
					<jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include>
				</div>
				
			</div>
			<div class="col-md-3" style="">
				<c:forEach items="${lastinfo.list }" var="l">
						<div class="media">
							<img src="/pic/${l.picture}" class="mr-3" alt="..." style="width: 156; height: 101;">
							<div class="media-body">
								<h5 class="mt-0"><a href="article?id=${l.id}" target="_blank">${l.title}</a></h5>
									<!-- 发布时间 -->
								<h5 class="mt-0">${l.user.username} <fmt:formatDate value="${l.created}" pattern="yyyy-MM-dd HH:mm:ss"/></h5>
							</div>
						</div><hr>
					</c:forEach>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		function goPage(page){
			alert(page)
			var categoryId='${article.categoryId}';
			var channelId='${article.channelId}';
			location.href="/?categoryId="+categoryId+"&channelId="+channelId+"&page="+page;
		}
	</script>
</body>
</html>