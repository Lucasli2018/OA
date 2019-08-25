<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>配置权限</title>
 	<%@include file="/WEB-INF/jsp/public/header.jsp" %>
 	<!-- 1，引入js文件与css文件 -->
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery_treeview/jquery.treeview.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/script/jquery_treeview/jquery.treeview.css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/file.css" />
	<script type="text/javascript">
		$(function(){
			//为所有的复选框绑定单击事件
			$('input[name=privilegeIds]').click(function(){
				//当选中某个权限时，同时选中其下级权限，当取消某个权限时，同时取消其下级权限
				$(this).siblings('ul').find('input').attr('checked',this.checked);

				//当选中某个权限时，同时选中其直接上级权限
				if(this.checked){
					$(this).parents('li').children('input').attr('checked',true);
				}else{
					//当取消某个权限时，如果同级权限都没有选中，则取消其上级权限
					var size = $(this).parent('li').siblings('li').children('input:checked').size();
					if(size == 0){
						$(this).parent().parent().siblings('input').attr('checked',false);
					}
				}
			});
		});
	</script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 配置权限
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <s:form action="role_setPrivilege" namespace="/" method="post">
    	<s:hidden name="id"></s:hidden>
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 正在为【${name }】配置权限 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
					<!--表头-->
					<thead>
						<tr align="LEFT" valign="MIDDLE" id="TableTitle">
							<td width="300px" style="padding-left: 7px;">
								<!-- 如果把全选元素的id指定为selectAll，并且有函数selectAll()，就会有错。因为有一种用法：可以直接用id引用元素 -->
								<input type="CHECKBOX" id="cbSelectAll" onclick="$('input[name=privilegeIds]').attr('checked',this.checked)"/>
								<label for="cbSelectAll">全选</label>
							</td>
						</tr>
					</thead>
                   
			   		<!--显示数据列表-->
					<tbody id="TableData">
						<tr class="TableDetail1">
							<!-- 显示权限树 -->
							<td>
								<%---
								<s:checkboxlist name="privilegeIds" list="privilegeList" listKey="id" listValue="name">
								</s:checkboxlist>
								 <s:iterator value="privilegeList">
								 <input 
								 	<s:property value="id in privilegeIds ? 'checked' : '' "/>
								 	value="${id }" type="checkbox" name="privilegeIds">${name }
								 <br/>
								 </s:iterator>
								 --%>
								 
								 <ul id="privilegeTree">
									 <s:iterator value="privilegeList">
									 	<li>
									 		 <input id="cb_${id }"
								 				<s:property value="id in privilegeIds ? 'checked' : '' "/>
								 					value="${id }" type="checkbox" name="privilegeIds">
								 					<label for='cb_${id }'><span class='folder' id='${id }'>${name }</span></label>
								 					
								 					<ul>
								 						<s:iterator value="children">
								 							<li>
								 								<input id="cb_${id }"
								 									<s:property value="id in privilegeIds ? 'checked' : '' "/>
								 										value="${id }" type="checkbox" name="privilegeIds">
								 										<label for='cb_${id }'><span class='folder' id='${id }'>${name }</span></label>
								 								<ul>
								 									<s:iterator value="children">
								 										<li>
								 											<input id="cb_${id }"
								 												<s:property value="id in privilegeIds ? 'checked' : '' "/>
								 													value="${id }" type="checkbox" name="privilegeIds">
								 													<label for='cb_${id }'><span class='folder' id='${id }'>${name }</span></label>
								 										</li>
								 									</s:iterator>
								 								</ul>
								 								
								 							</li>
								 						</s:iterator>
								 					</ul>
									 	</li>
									 </s:iterator>
								 </ul>
							</td>
						</tr>
					</tbody>
                </table>
            </div>
        </div>
        
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>
<script type="text/javascript">
	$("#privilegeTree").treeview();
</script>
</body>
</html>
