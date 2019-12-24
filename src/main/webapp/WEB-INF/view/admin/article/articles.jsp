<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="/resource/css/bootstrap.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div>
			标题<input type="form-control" name="title" class="name" value="${title}">&nbsp;
			<select name="status" class="form-control">
				<option value="0">待审</option>
				<option value="1">已审</option>
				<option value="-1">驳回</option>
				<option value="-1,0,1">全部</option>
			</select>
			<button type="button" class="btn btn-info" onclick="sel()">查询</button>
		</div>
		<br>
		<table class="table table-striped">
			<tr>
				<th>1</th>
				<th>2</th>
				<th>3</th>
				<th>4</th>
				<th>5</th>
				<th>6</th>
			</tr>
			<c:forEach items="${info.list}" var="list" varStatus="con">
				<tr>
					<td>${con.count}</td>
					<td>${list.title}</td>
					<td>${list.channel.name}</td>
					<td>${list.category.name}</td>
					<td>${list.user.username}</td>
					<td>${list.status==0?"待审":list.status==1?"已审":"驳回"}</td>
					<td><fmt:formatDate value="${list.created}"
							pattern="yyyy-MM-dd" /></td>
					
					<td><a href="/admin/article?id=${list.id}" target="_blank">详情</a>
						
						<c:if test="${list.hot==1}">
							<button type="button" class="btn btn-success" onclick="hot(${list.id},${list.hot})">热门</button>
						</c:if>
						<c:if test="${list.hot==0}">
							<button type="button" class="btn btn-danger" onclick="hot(${list.id},${list.hot})">非热门</button>
						</c:if>
					</td>
				</tr>
			</c:forEach>
			<td colspan="7"><jsp:include
					page="/WEB-INF/view/common/pages.jsp"></jsp:include></td>
		</table>
	</div>
	<script type="text/javascript">
		function goPage(page) {
			var url = "/admin/article/selects?pageNum=" + page;
			$("#center").load(url);
		}
		function sel(){
			var url = "/admin/article/selects?title=" + $(".name").val()+"&status="+$("[name='status']").val();
			$("#center").load(url);
		}
		function hot(i,id){
			var hot=id==0?1:0;
			location="/admin/upda?id="+i+"&hot="+hot+"";
		}
	</script>
</body>
</html>