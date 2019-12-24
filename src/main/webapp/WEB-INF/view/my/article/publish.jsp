<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String htmlData = request.getParameter("content") != null ? request.getParameter("content") : "";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<title>KindEditor JSP</title>

<link rel="stylesheet"
	href="/resource/kindeditor/themes/default/default.css" />
<link rel="stylesheet"
	href="/resource/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="/resource/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="/resource/kindeditor/lang/zh-CN.js"></script>
<script charset="utf-8"
	src="/resource/kindeditor/plugins/code/prettify.js"></script>
<script>
	KindEditor.ready(function(K) {
		window.editor1 = K.create('textarea[name="content"]', {
			cssPath : '/resource/kindeditor/plugins/code/prettify.css',
			uploadJson : '/resource/kindeditor/jsp/upload_json.jsp',
			fileManagerJson : '/resource/kindeditor/jsp/file_manager_json.jsp',
			allowFileManager : true,
			afterCreate : function() {
				var self = this;
				K.ctrl(document, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
			}
		});
		prettyPrint();
	});
</script>
</head>
<body>
	<%=htmlData%>
	<form id="example">
		<div class="form-group">
			<label for="t1">文章标题:</label><input id="t1" type="text"
				class="form-control" name="title">
		</div>
		<div class="form-group form-inline">
			<label>文章栏目:</label><select id="channelId" class="form-control" name="channelId">
				<option value="0">请选择</option>
			</select> 
			<label>文章分类:</label><select id="categoryId" class="form-control" name="categoryId">
				<option value="0">请选择</option>
			</select>
		</div>
		<div>
			文章图片<input type="file" name="myfile" class="form-control">
		</div>

		<textarea name="content" cols="100" rows="8"
			style="width: 100%; height: 260px; visibility: hidden;"><%=htmlspecialchars(htmlData)%></textarea>
		<br /> <input type="button" class="btn btn-info" name="button" value="提交内容" onclick="publish()" />
	</form>
	
	<script type="text/javascript">
		$.get("/my/Channel/selectsChannel",
			function(fhz){
				for ( var i in fhz) {
					$("#channelId").append("<option value='"+fhz[i].id+"'>"+fhz[i].name+"</option>");
				}
			},"json")
		$("#channelId").change(function(){
			var id=$(this).val();
			//因为会查找追加 所以 清空原有的 内容
			$("#categoryId").empty();
			$("#categoryId").append("<option value='"+0+"'>请选择</option>");
			$.get("/my/Category/selectsCategory",
					{id:id},
					function(fho){
						for ( var i in fho) {
							$("#categoryId").append("<option value='"+fho[i].id+"'>"+fho[i].name+"</option>");
						}
					},"json")
		})
		function publish(){
			//发布文章功能
			//alert(editor1.html()) //取到 我输入到富文本中的对象
			var forData=new FormData($("#example")[0]);//包含普通文本好文本对象
			forData.set("content",editor1.html());//把 我存入到富文本中内容 存入 表中的  content 属性中 存入的对象可能会有加粗等等的功能  所以怎么做
			
			 $.ajax({
				type:"post",
				url:"/my/Article/addpublish",
				data:forData,
				//告诉jQuery不要去处理发送的数据
				processData:false,
				//告诉jQuery不要去设置content-Type请求头
				contentType:false,
				success:function(flag){
					if(flag){
						alert("发布成功")
						$("#center").load("/my/article/articles");
						
					}else{
						alert("发布失败")
					}
				}
			}) 
		}
	</script>
</body>
</html>
<%!private String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}%>