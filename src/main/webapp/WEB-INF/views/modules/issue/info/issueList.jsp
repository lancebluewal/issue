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
		
		
		$(document).ready(function() {
			$('#changeProject').bind('change',function(){
				$('#searchForm').submit();
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/issue/info/list?status=${issueInfo.status}">缺陷列表</a></li>
		<c:if test="${issueInfo.status==0} "><li><a href="${ctx}/issue/info/form">缺陷添加</a></li></c:if>
	</ul>
	<form:form id="searchForm" modelAttribute="issueInfo" action="${ctx}/issue/info/list?status=${issueInfo.status}" method="post" >
		<div class="issueProject breadcrumb form-search">
		<form:select path="project.id" id="changeProject">
			<form:option value="">全部项目</form:option>
			<form:options items="${projects}" itemLabel="projectName" itemValue="id" htmlEscape="false"/>
		</form:select>
		<label class="clickView">(点击切换项目)</label>
		</div>
		<div class="breadcrumb form-search">
		<label>标题：</label><form:input path="issueTitle" type="text" />
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input type="submit" class="btn btn-primary" value="查询" />
		</div>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th><fmt:message key="BIAOTI"></fmt:message></th><th>项目</th><th>版本</th><th>严重程度</th><th>重现规律</th><th>缺陷类型</th><th>优先级</th><th>状态</th><th>创建人</th><th>处理人</th><th>操作</th></thead>
		<tbody>
			<c:forEach items="${page.list}" var="issue">
				<tr>
					<td><a href="${ctx}/issue/info/view?id=${issue.id}" >${issue.issueTitle }</a></td>
					<td>${issue.project.projectName}</td>
					<td>${issue.projectVersion.versionNo }</td>
					<td>${issue.issueLevel.name }</td>
					<td>${issue.issueRegularity.name }</td>
					<td>${issue.issueType.name }</td>
					<td>${issue.issuePriority.name }</td>
					<td>${fns:getDictLabel(issue.status, 'issue_status', '新')}</td>
					<td>${issue.createBy.name}</td>
					<td>(${issue.office.name })-${issue.user.name }</td>
					<td><c:if test="${issueInfo.status==0}"><a href="${ctx}/issue/info/form?id=${issue.id}">编辑</a></c:if>&nbsp;<a href="${ctx}/issue/info/allot?id=${issue.id}">分配</a>&nbsp;<a href="${ctx}/issue/info/del?id=${issue.id}" onclick="return confirmx('确认要${issue.delFlag ne 0?'恢复':''}删除该记录吗？', this.href)">删除</a></td>
				</tr>
			</c:forEach>
		<c:if test="${fns:listLength(page.list)==0}">
			<tr>
				<td colspan="9" style="text-align: center;">没有数据</td>
			</tr>
		</c:if>
		</tbody>
	</table>
	<c:if test="${fns:listLength(page.list)>0}">
		<div class="pagination">${page}</div>
	</c:if>
</body>
</html>