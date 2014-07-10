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
		<li class="active"><a href="${ctx}/issue/info/completelist?status=4">缺陷列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="history" action="${ctx}/issue/info/completelist?status=4" method="post" >
		<div class="issueProject breadcrumb form-search">
		<form:select path="info.project.id" id="changeProject">
			<form:option value="">全部项目</form:option>
			<form:options items="${projects}" itemLabel="projectName" itemValue="id" htmlEscape="false"/>
		</form:select>
		<label class="clickView">(点击切换项目)</label>
		</div>
		<div class="breadcrumb form-search">
		<label>标题：</label><form:input path="info.issueTitle" type="text" />
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input type="submit" class="btn btn-primary" value="查询" />
		</div>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th><fmt:message key="BIAOTI"></fmt:message></th><th>项目</th><th>版本</th><th>严重程度</th><th>重现规律</th><th>缺陷类型</th><th>优先级</th><th>操作</th></thead>
		<tbody>
			<c:forEach items="${page.list}" var="history">
				<tr>
					<td><a href="${ctx}/issue/info/view?id=${history.info.id}" >${history.info.issueTitle }</a></td>
					<td>${history.info.project.projectName}</td>
					<td>${history.info.projectVersion.versionNo }</td>
					<td>${history.info.issueLevel.name }</td>
					<td>${history.info.issueRegularity.name }</td>
					<td>${history.info.issueType.name }</td>
					<td>${history.info.issuePriority.name }</td>
					<td><a href="${ctx}/issue/info/view?id=${history.info.id}" >查看</a></td>
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