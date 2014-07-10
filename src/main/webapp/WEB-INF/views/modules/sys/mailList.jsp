<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/dialog.jsp" %>
	<style type="text/css">.sort{color:#0663A2;cursor:pointer;}</style>
	<script type="text/javascript">
		$(document).ready(function() {
		
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/sys/user/");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/mail/">邮件配置列表</a></li>
		<li><a href="${ctx}/sys/mail/form">邮件配置添加</a></li>
	</ul>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>SMTP</th><th>端口</th><th>用户名</th><td>使用状态</td><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page}" var="mail">
			<tr>
				<td>${mail.smtp}</td>
				<td>${mail.port}</td>
				<td>${mail.username}</td>
				<td>${fns:getDictLabel(mail.asdefault, 'mail_status', '未使用')}</td>
				<td>
   				<a href="${ctx}/sys/mail/form?id=${mail.id}">修改</a>
				<a href="${ctx}/sys/mail/delete?id=${mail.id}" onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>