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
		<div class="container-fluid">
			<form id="form1">
				<div class="form-group form-inline">

					举报类型:<select name="typename" class="form-control" id="typename">
						<option value="">所有</option>
						<option>垃圾广告</option>
						<option>政治反动</option>
						<option>钓鱼网站</option>
					</select> 投诉次数：<input class="form-control" type="text" name="startNum"
						value="${complainVO.startNum}">-- <input
						class="form-control" type="text" name="endNum"
						value="${complainVO.endNum}">
				</div>
				<div class="form-group form-inline">
					投诉日期：<input class="form-control" type="text" name="startTime"
						value="${ complainVO.startTime }">-- <input
						class="form-control" type="text" name="endTime"
						value="${ complainVO.endTime }">

					<button class="btn btn-info" type="button" onclick="query()">查询</button>
				</div>
			</form>
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
					<td>${list.article.title}</td>
					<td>${list.url}</td>
					<td>${list.typename}</td>
					<td>${list.content}</td>
					<td>${list.user.username}</td>
					<td>${list.complain_num}</td>
					<td>${list.created}</td>
					${list.article.status }
					<td><c:if test="${list.article.status==1}">
							<button type="button" class="btn btn-success"
								onclick="update(${list.articleId},this)">正常</button>

						</c:if> <c:if test="${list.article.status==2}">
							<button type="button" class="btn btn-warning"
								onclick="update(${list.articleId},this)">停用</button>

						</c:if></td>

				</tr>
			</c:forEach>
			<td colspan="7"><jsp:include
					page="/WEB-INF/view/common/pages.jsp"></jsp:include></td>
		</table>
	</div>
	<script type="text/javascript">
		function goPage(page) {
			var url = "/admin/article/selectComplain?pageNum=" + page;
			$("#center").load(url);
		}
		 function query(){
			 //获取举报类型
			 var  url="/admin/article/selectComplain?"+$("#form1").serialize();
			 $("#center").load(url)
		 }
		 function update(id,obj){
				//要改变为的状态
				var status =$(obj).text()=="正常"?2:1;
				
				$.post("/admin/article/update",{id:id,status:status},function(flag){
					if(flag){
						//alert("操作成功");
						//改变内容
						$(obj).text(status==2?"停用":"正常");
						//改变颜色
						$(obj).attr("class",status==2?"btn btn-warning":"btn btn-success")
					}else{
						alert("操作失败")
					}
				})
				
				
			}
	</script>
</body>
</html>