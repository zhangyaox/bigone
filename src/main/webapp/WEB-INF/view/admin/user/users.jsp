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
			用户名<input type="form-control" name="name" class="name" value="${name}">
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
					<td>${list.username}</td>
					<td>${list.nickname}</td>
					<td><fmt:formatDate value="${list.birthday}"
							pattern="yyyy-MM-dd" /></td>
					<td><fmt:formatDate value="${list.created}"
							pattern="yyyy-MM-dd" /></td>
					<td>
						<c:if test="${list.locked==0}">
							<button type="button" class="btn btn-success" onclick="btn(${list.id},this)">正常</button>
						</c:if>
						<c:if test="${list.locked==1}">
							<button type="button" class="btn btn-danger" onclick="btn(${list.id},this)">停用</button>
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
			//location = "/user/selects?pageNum=" + page
			var url = "/admin/user/selects?pageNum=" + page;
			$("#center").load(url);
		}
		function sel(){
			var url = "/admin/user/selects?name=" + $(".name").val();
			$("#center").load(url);
		}
		function btn(id,obj){
			// 0 正常  1停用
			var locked=$(obj).text()=="正常"?1:0;
			
			 $.post("/admin/user/update",
					{id:id,locked:locked},
					function(fhz){
						if(fhz){
							alert("成功");
							$(obj).text(locked==0?"正常":"停用");//当前对象的 文本里面的
							$(obj).attr("class",locked==0?"btn btn-success":"btn btn-danger")//修改当前对象的 class 的对应的按钮的颜色
						}else{
							alert("失败");
						}
					},"json")
		}
	</script>
</body>
</html>