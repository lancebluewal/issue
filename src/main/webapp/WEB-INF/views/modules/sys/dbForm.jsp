<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/db/">数据库备份</a></li>
	</ul><br/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<tags:message content="${message}"/>
		<thead><tr><th>文件名</th><th>操作</th></thead>
		<tbody>
			<c:forEach items="${page.list}" var="db">
				<tr>
					<td>${db.fileName }</td>
					<td><a target="_blank" href="${db.filePath}">下载</a></td>
				</tr>
			</c:forEach>
		<c:if test="${fns:listLength(page.list)==0}">
			<tr>
				<td colspan="9" style="text-align: center;">没有数据</td>
			</tr>
		</c:if>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
		<form:form id="inputForm" action="${ctx}/sys/db/back" method="post" class="form-horizontal">
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="submit" value="备 份"/>
		</div>
	</form:form>
</body>
</html>