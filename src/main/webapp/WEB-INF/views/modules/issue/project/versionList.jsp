<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	
		function ininHiddenVersionStatusValue(versionStatus){
			$('#hiddenVersionStatus').val(versionStatus);
		}
		
		function ininHiddenVersionNoValue(versionNo){
			$('#hiddenVersionNo').val(versionNo);
		}
	
		function del(versionId){
			top.$.jBox.confirm('确认要删除该记录吗？','系统提示',function(v,h,f){
				if(v=='ok'){
					$.ajax({ 
			            type: "post", 
		                url: "${ctx}/issue/project/version/del", 
		                dataType: "json", 
		                data:"id="+versionId+"&projectId=${issueProject.id}",
		                success: function (data) { 
		                        if(data['result']=="true"){
			                		$('#contentTable tbody').empty();
		                        	for(var i=0;i<data['value'].length;i++){
		                        		var tr = $('<tr><td>'+data['value'][i]['versionNo']+'</td><td>'+"<input type='button' value='删除' onclick=\"del('"+data['value'][i]['id']+"')\" /><input type='button' value='编辑'   onclick=\"edit('"+data['value'][i]['id']+"')\" />"+'</td></tr>');
		                        		tr.appendTo($('#contentTable tbody'));
		                        	}
		                        	
		                        };
		                }, 
		                error: function (XMLHttpRequest, textStatus, errorThrown) { 
		                        alert(errorThrown); 
		                } 
		        	});
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		}
		
		function edit(versionId,projectId){
			top.$.jBox.open("iframe:${ctx}/issue/project/version/form?id="+versionId, "版本管理",$(top.document).width()-220,$(top.document).height()-180,{
				buttons:{"确定":true}, loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				},
				submit: function (v, h, f) {
		            if (v) {
		            	$.ajax({ 
				            type: "post", 
			                url: "${ctx}/issue/project/version/save", 
			                dataType: "json", 
			                data:"versionId="+versionId+"&id="+projectId+"&versionNo="+$('#hiddenVersionNo').val()+"&status="+$('#hiddenVersionStatus').val(),
			                success: function (data) { 
			                	location = "${ctx}/issue/project/version?id="+projectId;
			                }, 
			                error: function (XMLHttpRequest, textStatus, errorThrown) { 
			                        //alert(errorThrown); 
			                	location = "${ctx}/issue/project/version?id="+projectId;
			                } 
			        	});
		            	
		            	
		               
		               return true;
		            }
		            return false;
		        }
			});
					/*$.ajax({ 
			            type: "post", 
		                url: "${ctx}/issue/project/version/edit", 
		                dataType: "json", 
		                data:"id="+versionId,
		                success: function (data) { 
		                       $('#versionNo').val(data['versionNo']);
		                       $('#versionId').val(data['id']);
		                       $('#btnSubmit').val('更  新');
		                }, 
		                error: function (XMLHttpRequest, textStatus, errorThrown) { 
		                        alert(errorThrown); 
		                } 
		        	});
					*/
		}
		
	
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
			
			/*
			$("#btnSubmit").bind("click",function(){
				$.ajax({ 
			            type: "post", 
                        url: "${ctx}/issue/project/version/save", 
                        dataType: "json", 
                        data:"id=${project.id}&versionNo="+$("#versionNo").val()+"&versionId="+ $('#versionId').val(),
                        success: function (data) { 
                                if(data['result']=="true"){
                                	location = "${ctx}/issue/project/version?id=${project.id}";
                                };
                        }, 
                        error: function (XMLHttpRequest, textStatus, errorThrown) { 
                                //alert(errorThrown); 
                        } 
                });
				
				$("#versionNo").val('');
				 $('#btnSubmit').val('添  加');
				 $('#versionId').val('');
			});
			*/
			
			
			$("#relationButton").click(function(){
				top.$.jBox.open("iframe:${ctx}/cms/article/selectList?pageSize=8", "添加相关",$(top.document).width()-220,$(top.document).height()-180,{
					buttons:{"确定":true}, loaded:function(h){
						$(".jbox-content", top.document).css("overflow-y","hidden");
					}
				});
			});
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<ul class="nav nav-tabs">
			<li ><a href="${ctx}/issue/project/list">项目列表</a></li>
			<li class="active"><a href="${ctx}/issue/project/version?id=${version.id}">版本管理</a></li>
		</ul>
	</ul><br/>
		<form:form id="inputForm" action="${ctx}/issue/project/version/save" method="post" class="form-horizontal breadcrumb form-search">
			<div class="issueProject ">
				<label>(项目名称)&nbsp;${issueProject.projectName }</label>
				<br/>
				 <input id="btnSubmit" class="btn btn-primary" type="button" value="版  本  添  加" onclick="edit('','${issueProject.id }')"/>
			</div>
			<input type="hidden" id="hiddenVersionNo"  />
			<input type="hidden" id="hiddenVersionStatus"  />
		</form:form>
		
		
		<form:form id="inputForm" modelAttribute="version" action="${ctx}/issue/project/version?id=${issueProject.id}" method="post" class="form-horizontal breadcrumb form-search">
			<label>版本号：</label><form:input path="versionNo"/><label>版本状态：</label><form:select path="status">
				<form:option value=""></form:option>
				<form:options items="${fns:getDictList('project_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<input type="submit" class="btn btn-primary" value="查询" />
		</form:form>
		<tags:message content="${message}"/>
		<div class="form-actions">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead><tr><th>版本</th><th>状态</th><th>操作</th></thead>
				<tbody>
					<c:forEach items="${projectVersions}" var="projectVersion">
					<tr>
						<td>${projectVersion.versionNo }</td>
						<td>${fns:getDictLabel(projectVersion.status,"project_status","立项") }</td>
						<td><input type="button" value="删除" onclick="del('${projectVersion.id }')" /><input type="button" value="编辑" onclick="edit('${projectVersion.id }','${issueProject.id }')" /></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	
</body>
</html>