<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	
		$(document).ready(function() {
			top.mainFrame.ininHiddenVersionNoValue($('#versionNo').val());
			top.mainFrame.ininHiddenVersionStatusValue($('input[name="status"]').val());
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
			
			
			$('#versionNo').bind('change',function(){
				top.mainFrame.ininHiddenVersionNoValue($(this).val());
			});
			
			$('input[name="status"]').bind('change',function(){
				top.mainFrame.ininHiddenVersionStatusValue($(this).val());
			});
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">版本修改</li>
	</ul><br/>
		<form:form id="inputForm" modelAttribute="version" action="#" method="post" class="form-horizontal">
			<div class="control-group">
				<label class="control-label">版本号:</label>
				<div class="controls">
					<form:input path="versionNo"  htmlEscape="false" maxlength="50" class="required" id="versionNo" />
	                <input type="hidden" id="versionId" />
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label">版本号:</label>
				<div class="controls">
					<form:radiobuttons  path="status" items="${fns:getDictList('project_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</div>
			</div>
		</form:form>
	
</body>
</html>