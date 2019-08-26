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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 用户管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
                <td width="100">登录名</td>
                <td width="100">姓名</td>
                <td width="100">所属部门</td>
                <td width="200">岗位</td>
                <td>备注</td>
                <td>相关操作</td>
            </tr>
        </thead>
        
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="userList">
        	<s:iterator value="recordList">
	            <tr class="TableDetail1 template">
	                <td>${loginName}&nbsp;</td>
	                <td>${name}&nbsp;</td>
	                <td>${department.name}&nbsp;</td>
	                <td>
	                	<s:iterator value="roles">
	                		${name }&nbsp;
	                	</s:iterator>
	                &nbsp;</td>
	                <td>${description}&nbsp;</td>
	                <td>
	                	<s:if test="#session.loginUser.hasPrivilegeByName('用户删除')">
		                	<s:a onclick="return window.confirm('确定删除当前记录吗？')" action="user_delete?id=%{id}" namespace="/">删除</s:a>
	                	</s:if>
	                	<s:if test="#session.loginUser.hasPrivilegeByName('用户修改')">
		                    <s:a action="user_editUI?id=%{id}" namespace="/">修改</s:a>
	                	</s:if>
	                	<s:if test="#session.loginUser.hasPrivilegeByName('用户初始化密码')">
							<s:a action="user_intiPassword?id=%{id}" namespace="/" onclick="return window.confirm('您确定要初始化密码为1234吗？')">初始化密码</s:a>
	                	</s:if>
	                </td>
	            </tr>
        	</s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <s:if test="#session.loginUser.hasPrivilegeByName('用户添加')">
	    <div id="TableTail">
	        <div id="TableTail_inside">
	            <s:a action="user_addUI" namespace="/"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></s:a>
	        </div>
	    </div>
    </s:if>
</div>

<%@include file="/WEB-INF/jsp/public/pageView.jsp" %>
<s:form id="pageForm" action="user_list" namespace="/"></s:form>
</body>
</html>


