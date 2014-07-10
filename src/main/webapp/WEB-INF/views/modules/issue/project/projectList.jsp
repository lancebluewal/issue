<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>链接管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/issue/project/list">项目列表</a></li>
		<li><a href="${ctx}/issue/project/form">项目添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="issueProject" action="${ctx}/issue/project/list" method="post" class="breadcrumb form-search">
		
		<label>项目名称：</label><form:input path="projectName"/>
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input type="submit" class="btn btn-primary" value="查询" />
		
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>项目名称</th><th>状态</th><th>操作</th></thead>
		<tbody>
			<c:forEach items="${page.list}" var="project">
				<tr>
					<td>${project.projectName }</td>
					<td>${fns:getDictLabel(project.status,"project_status","立项") }</td>
					<td><a href="${ctx}/issue/project/form?id=${project.id}">编辑</a>&nbsp;<a href="${ctx}/issue/project/del?id=${project.id}" onclick="return confirmx('确认要${project.delFlag ne 0?'恢复':''}删除该记录吗？', this.href)">删除</a>&nbsp;<a href="${ctx}/issue/project/version?id=${project.id}">版本管理</a>&nbsp;<a href="${ctx}/issue/project/user?id=${project.id}">人员管理</a></td>
				</tr>
			</c:forEach>
		<c:if test="${fns:listLength(page.list)==0}">
			<tr>
				<td colspan="3" style="text-align: center;">没有数据</td>
			</tr>
		</c:if>
		</tbody>
	</table>
	<c:if test="${fns:listLength(page.list)>0}">
		<div class="pagination">${page}</div>
	</c:if>
</body>
</html>