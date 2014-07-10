<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<ul class="nav nav-tabs">
			<li ><a href="${ctx}/issue/project/list">项目列表</a></li>
			<li class="active"><a href="${ctx}/issue/project/form">项目添加</a></li>
		</ul>
	</ul><br/>
	
		<form:form id="inputForm" modelAttribute="issueProject" action="${ctx}/issue/project/save" method="post" class="form-horizontal">
			<form:hidden path="id"/>
			<tags:message content="${message}"/>
			<div class="control-group">
				<label class="control-label">标题:</label>
				<div class="controls">
	                <form:input path="projectName" htmlEscape="false" maxlength="50" class="required"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">项目状态:</label>
				<div class="controls">
	                <form:select path="status">
					<form:option value=""></form:option>
					<form:options items="${fns:getDictList('project_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				</div>
			</div>
			<div class="form-actions">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
		</form:form>
	
</body>
</html>