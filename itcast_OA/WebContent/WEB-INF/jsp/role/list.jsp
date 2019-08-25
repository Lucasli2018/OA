<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>岗位列表</title>
   <%@include file="/WEB-INF/jsp/public/header.jsp" %>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 岗位管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td width="200px">岗位名称</td>
                <td width="300px">岗位说明</td>
                <td>相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="roleList">
        	<s:iterator value="list">
				<tr class="TableDetail1 template">
					<td>${name}&nbsp;</td>
					<td>${description}&nbsp;</td>
					<td><s:a onclick="return window.confirm('确定删除当前记录吗？')" action="role_delete?id=%{   id   }" namespace="/">删除</s:a>
						<s:a action="role_editUI?id=%{  id  }" namespace="/">修改</s:a>
						<s:a action="role_setPrivilegeUI?id=%{id}" namespace="/">设置权限</s:a>
					</td>
				</tr>
        	</s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <a href="${pageContext.request.contextPath}/role_addUI.do"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></a>
        </div>
    </div>
</div>
</body>
</html>

