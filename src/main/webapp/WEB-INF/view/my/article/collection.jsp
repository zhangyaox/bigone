<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>收藏</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="/resource/css/bootstrap.css" rel="stylesheet">
</head>
<body>
		${info}
		<div class="container">

		<c:forEach items="${info.list}" var="li">
			<ul class="list-unstyled">
					<div class="media-body">
						<h5 class="mt-0 mb-1"><a href="${li.url}" target="_blank">${li.text}</a></h5>
						时间:<div style="margin-top: 60px"><fmt:formatDate value="${li.created}" pattern="yyyy-MM-dd HH:mm:dd"/></div>
						<input type="button" value="删除" onclick="del(${li.id})">
					</div>
			</ul>
		</c:forEach>

		<jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include></td>
		
	</div>
	<script type="text/javascript">
		function goPage(page) {
			var url = "/my/article/collection?pageNum=" + page;
			$("#center").load(url);
		}
		function sel(){
			var url = "/admin/article/selects?title=" + $(".name").val()+"&status="+$("[name='status']").val();
			$("#center").load(url);
		}
		function del(id){
			
			$.post("/my/article/delcollection",
					{id:id},
					function(fhz){
						if(fhz){
							alert("成功")
							
						}else{
							
						}
					},"json")
		}
	</script>
</body>
</html>