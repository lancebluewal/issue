<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
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
			
			$('#project').bind('click',function(){
				var projectId = $('#project').val();
               	$('#projectVersion').empty();
				$('#projectVersion').val('');
				$("<option value=\"\"></option>").appendTo($('#projectVersion'));
				$.ajax({ 
		            type: "post", 
	                url: "${ctx}/issue/project/version/list", 
	                dataType: "json", 
	                data:"id="+projectId,
	                success: function (data) { 
                        if(data['result']=="true"){
                        	for(var i=0;i<data['value'].length;i++){
                        		var option = $("<option value='"+data['value'][i]['id']+"'>"+data['value'][i]['versionNo']+"</option>");
                        		option.appendTo($('#projectVersion'));
                        	}
                        	
                        };
	                }, 
	                error: function (XMLHttpRequest, textStatus, errorThrown) { 
	                        alert(errorThrown); 
	                } 
	        	});
				
			});
			
			$('#group').bind('click',function(){
				var projectId = $('#group').val();
               	$('#user').empty();
				$('#user').val('');
				$("<option value=\"\"></option>").appendTo($('#user'));
				$.ajax({ 
		            type: "post", 
	                url: "${ctx}/issue/project/user/list", 
	                dataType: "json", 
	                data:"id="+projectId,
	                success: function (data) { 
                        if(data['result']=="true"){
                        	for(var i=0;i<data['value'].length;i++){
                        		var option = $("<option value='"+data['value'][i]['id']+"'>"+data['value'][i]['name']+"</option>");
                        		option.appendTo($('#user'));
                        	}
                        	
                        };
	                }, 
	                error: function (XMLHttpRequest, textStatus, errorThrown) { 
	                        alert(errorThrown); 
	                } 
	        	});
				
			});
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<ul class="nav nav-tabs">
			<li ><a href="${ctx}/issue/info/list?status=0">缺陷列表</a></li>
			<li class="active"><a href="${ctx}/issue/info/form">缺陷添加</a></li>
		</ul>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="issueInfo" action="${ctx}/issue/info/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">标题:</label>
			<div class="controls">
                <form:input path="issueTitle" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属项目:</label>
			<div class="controls">
                <form:select path="project.id" id="project" class="required">
					<form:option value=""></form:option>
					<form:options items="${projects}" itemLabel="projectName" itemValue="id" htmlEscape="false"/>
				</form:select>
				<label>项目版本:</label>
                <form:select path="projectVersion.id" id="projectVersion" class="required">
                	<form:option value=""></form:option>
					<form:options items="${projectVersions}" itemLabel="versionNo" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">缺陷描述:</label>
			<div class="controls">
				<form:textarea id="content" htmlEscape="true" path="content" rows="4" maxlength="200" class="input-xxlarge"/>
				<tags:ckeditor replace="content" uploadPath="/issue/info" />
			</div>
		</div>
		<!-- 
		<div class="control-group">
			<label class="control-label">分配组:</label>
			<div class="controls">
                <form:select path="office.id" id="group" >
					<form:option value=""></form:option>
					<form:options items="${offices}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<label>分配人员:</label>
                <form:select path="user.id" id="user">
                	<form:option value=""></form:option>
					<form:options items="${users}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		 -->
		<div class="control-group">
			<label class="control-label">缺陷程度:</label>
			<div class="controls">
                <form:select path="issueLevel.id">
					<form:option value=""></form:option>
					<form:options items="${levels}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<label>重现规律:</label>
                <form:select path="issueRegularity.id">
                	<form:option value=""></form:option>
					<form:options items="${regularitys}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">缺陷类型:</label>
			<div class="controls">
                <form:select path="issueType.id" >
					<form:option value=""></form:option>
					<form:options items="${types}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
				
				&nbsp;&nbsp;&nbsp;&nbsp;
				<label>优&nbsp;&nbsp;先&nbsp;级:</label>
                <form:select path="issuePriority.id">
                	<form:option value=""></form:option>
					<form:options items="${prioritys}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<!-- 
		<div class="control-group">
			<label class="control-label">缩略图:</label>
			<div class="controls">
                <form:hidden  path="image" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<tags:ckfinder input="image" type="thumb" uploadPath="/issue/info" selectMultiple="true"/>
			</div>
		</div>
		 -->
		<div class="control-group">
			<label class="control-label">附件:</label>
			<div class="controls">
				<form:hidden path="attachment" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<tags:ckfinder input="attachment" type="files" uploadPath="/issue/info" selectMultiple="true" />
				<span class="help-inline">不能为中文文件名文件</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮件通知:</label>
			<div class="controls">
				<form:radiobuttons path="notice" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</div>
		</div>
		<!-- 
		<div class="control-group">
			<label class="control-label">操作状态:</label>
				<div class="controls">
				<form:select path="status" >
					<form:options items="${fns:getDictList('issue_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		 -->
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="发  布"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>