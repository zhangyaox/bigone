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

		<c:forEach items="${info.list}" var="li">
			<ul class="list-unstyled">
				<li class="media"><img src="/pic/${li.picture}" style="width: 150px;height: 120px" class="mr-3" alt="...">
					<div class="media-body">
						<h5 class="mt-0 mb-1"><a href="/my/article/selectById?id=${li.id}" target="_blank">${li.title}</a></h5>
						<div style="margin-top: 60px"><fmt:formatDate value="${li.created}" pattern="yyyy-MM-dd HH:mm:dd"/></div>
						<span style="float: right;">
							<c:if test="${li.deleted==0}">
								<button type="button" class="btn-sm btn-danger" onclick="del(${li.id},this)">删除</button>
							</c:if>
							<c:if test="${li.deleted!=0}">
								<button type="button" class="btn btn-success" onclick="del(${li.id},this)">恢复</button>
							</c:if>
						</span>
					</div></li><hr>
			</ul>
		</c:forEach>

		<jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include></td>
		
	</div>
	<script type="text/javascript">
		function goPage(page) {
			var url = "/my/article/articles?pageNum=" + page;
			$("#center").load(url);
		}
		function sel(){
			var url = "/admin/article/selects?title=" + $(".name").val()+"&status="+$("[name='status']").val();
			$("#center").load(url);
		}
		function del(id,thiz){
			var del=$(thiz).text()=="删除"?1:0;
			
			$.post("/my/article/update",
					{id:id,deleted:del},
					function(fhz){
						if(fhz){
							alert("成功")
							$(thiz).text(del==0?"删除":"恢复");
							$(thiz).attr("class",del==0?"btn-sm btn-danger":"btn btn-success")
						}else{
							
						}
					},"json")
		}
	</script>
</body>
</html>