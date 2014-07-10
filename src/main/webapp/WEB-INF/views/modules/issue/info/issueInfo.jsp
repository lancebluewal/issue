<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>机构管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/tab/js/jquery.tabso_yeso.js"></script>
<link href="${ctxStatic}/tab/css/lanrenzhijia.css" type="text/css"
	rel="stylesheet" />
<script type="text/javascript">
	$(document).ready(
			function() {
				
				$('#hello').flash({
				    src: '${ctxStatic}/flash/clockspy.swf',
				    width: 320,
				    height: 240
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
				
				//上下滑动选项卡切换
				$("#move-animate-top").tabso({
					cntSelect : "#topcon",
					tabEvent : "mouseover",
					tabStyle : "move-animate",
					direction : "top"
				});

				$("#name").focus();
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
										} else {
											error.insertAfter(element);
										}
									}
								});

			});
</script>
<style type="text/css">
.baseInfo {
	position: relative;
	float: left;
	width: 600px;
	height: 400px;
	border: 0px solid red;
	float: left;
}

.operationInfo1 {
	position: relative;
	float: left;
	width: 400px;
	height: 400px;
	border: 0px solid red;
}

.operationInfo {
	position: relative;
	float: left;
	width: 998px;
	border-width: 0px 1px 1px 1px;
	border-color: #ddd;
	border-style: solid;
}

.issueInfoContainer {
	margin-left: 50px;
	width: 1200px;
	height: 900px;
	min-width: 1200px;
}

.operationInfoContainer {
	margin-left: -25px;
}

.left {
	float: left;
}

.right {
	float: right;
}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<ul class="nav nav-tabs">
			<li><a href="${ctx}/issue/info/list">缺陷列表</a></li>
			<li class="active"><a
				href="${ctx}/issue/info/view?id=${history.info.id}">缺陷信息</a></li>
		</ul>
	</ul>
	<br />
	<div class="issueInfoContainer">
		<div class="baseInfo">
			<ul class="tabbtn" id="move-animate-top">
				<li class="current"><a>缺陷描述</a></li>
				<li><a>操作汇总</a></li>
				<li><a>变更历史</a></li>
			</ul>
			<!--tabbtn end-->
			<div class="tabcon" id="topcon">
				<div class="subbox">
					<div class="sublist">
						<ul>
							<li><span>▪</span><a href="http://www.lanrenzhijia.com.com/"
								target="_blank">jquery表格变色隔行变色鼠标滑过竖直表格内容变色效果</a></li>
							<li><span>▪</span><a href="http://www.lanrenzhijia.com.com/"
								target="_blank">jQuery浮动层点击图标按钮关闭或展开</a></li>
							<li><span>▪</span><a href="http://www.lanrenzhijia.com.com/"
								target="_blank">jquery表格变色隔行变色鼠标滑过竖直表格内容变色效果</a></li>
						</ul>
					</div>
					<!--tabcon end-->
					<div class="sublist">
						<ul>
							<li><span>▪</span><a href="http://www.lanrenzhijia.com.com/"
								target="_blank">jquery表格变色隔行变色鼠标滑过竖直表格内容变色效果</a></li>
							<li><span>▪</span><a href="http://www.lanrenzhijia.com.com/"
								target="_blank">jQuery浮动层点击图标按钮关闭或展开</a></li>
						</ul>
					</div>
					<!--tabcon end-->
					<div class="sublist">
						<ul>
							<c:forEach items="${issueInfo.history}" var="history">
								<li><span class="left">▪</span><label class="left">${history.remarks}</label><label
									class="right">${fns:coverDate(history.createDate)}</label></li>
							</c:forEach>

						</ul>
					</div>
					<!--tabcon end-->
				</div>
				<!--tabcon end-->
			</div>

		</div>
		<div class="operationInfo1">
			<div class="operationInfoContainer">
				<form:form id="inputForm" modelAttribute="issueInfo" method="post">
					<ul class="tabbtn" id="move-animate-top">
						<li class="current"><a>基本信息</a></li>
					</ul>
					<!--tabbtn end-->
					<div class="tabcon" id="topcon">
						<div class="subbox">
							<div class="sublist">
								<ul>
									<li><label class="control-label">项&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;目:</label> <label>${issueInfo.id}</label></li>
									<li><label class="control-label">版&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本:</label> <label>${issueInfo.projectVersion.versionNo}</label></li>
									<li><label class="control-label">模&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;块:</label> <label>${issueInfo.id}</label></li>
									<li><label class="control-label">处&nbsp;&nbsp;理&nbsp;&nbsp;人:</label> <label>${issueInfo.user.name}</label></li>
									<li><label class="control-label">创&nbsp;&nbsp;建&nbsp;&nbsp;人:</label> <label>${issueInfo.createBy.name}</label></li>
									<li><label class="control-label">创建时间:</label> <label>${fns:coverDate(issueInfo.createDate)}</label></li>
									<li><label class="control-label">严重程度:</label> <label>${issueInfo.issueLevel.name}</label></li>
									<li><label class="control-label">重现规律:</label> <label>${issueInfo.issueRegularity.name}</label></li>
									<li><label class="control-label">缺陷类型:</label> <label>${issueInfo.issueType.name}</label></li>
									<li><label class="control-label">优&nbsp;&nbsp;先&nbsp;&nbsp;级:</label> <label>${issueInfo.issuePriority.name}</label></li>
								</ul>
							</div>

						</div>
						<!--tabcon end-->
					</div>
				</form:form>
			</div>
		</div>
			<div class="operationInfo">
		<form:form id="inputForm" modelAttribute="history" action="${ctx}/issue/info/history/save" method="post" class="form-horizontal">
		
				<div class="control-group">
					<label class="control-label">状态流转:</label>
					<div class="controls">
						<form:radiobuttons path="status"
							items="${fns:getDictList('issue_status')}" itemLabel="label"
							itemValue="value" htmlEscape="false"  class="required"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">分配组:</label>
					<div class="controls">
						<form:select path="info.office.id" id="group">
							<form:option value=""></form:option>
							<form:options items="${offices}" itemLabel="name" itemValue="id"
								htmlEscape="false"  class="required"/>
						</form:select>
						&nbsp;&nbsp;&nbsp;&nbsp; <label>分配人员:</label>
						<form:select path="info.user.id" id="user">
							<form:option value=""></form:option>
							<form:options items="${users}" itemLabel="name" itemValue="id"
								htmlEscape="false"  class="required"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">处理意见:</label>
					<div class="controls">
						<form:textarea path="remarks" />
					</div>
				</div>
				<div class="form-actions">
					<form:hidden path="info.id" />
					<input id="btnSubmit" class="btn btn-primary" type="submit"
						value="提  交" />&nbsp; <input id="btnCancel" class="btn"
						type="button" value="返 回" onclick="history.go(-1)" />
				</div>


		</form:form>
		<div id="hello"></div>
			</div>
	</div>

</body>
</html>