<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>导航菜单</title>
		<%@include file="/WEB-INF/jsp/public/header.jsp" %>
		<script language="JavaScript" src="${pageContext.request.contextPath }/script/menu.js"></script>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/style/blue/menu.css" />
	</head>
	<body style="margin: 0">
		<div id="Menu">
		    <ul id="MenuUl">
		    	<s:iterator value="#application.privilegeTopList">
		    		<!-- 从Session中获取登录用户，根据用户的角色最终获取对应的权限，判断此权限是否和当前循环出的权限是否一致，如果一致就显示 -->
		    		<!-- 使用OGNL调用对象的方法 -->
		    		<s:if test="#session.loginUser.hasPrivilegeByName(name)">
				        <li class="level1">
				            <div onclick="menuClick(this);" class="level1Style">
				            	<img src="${pageContext.request.contextPath }/style/images/MenuIcon/${id }.gif" class="Icon" /> ${name }
				            </div>
				            <ul style="display: none;" class="MenuLevel2">
				            	<s:iterator value="children">
				            		<s:if test="#session.loginUser.hasPrivilegeByName(name)">
					                <li class="level2">
					                    <div class="level2Style">
					                    	<img src="${pageContext.request.contextPath }/style/images/MenuIcon/menu_arrow_single.gif" /> 
					                    	<a target="right" href="${pageContext.request.contextPath }${url}.do">${name }</a>
					                    </div>
					                </li>
					                </s:if>
				            	</s:iterator>
				            </ul>
				        </li>
		    		</s:if>
		        </s:iterator>
		    </ul>
		</div>
	</body>
</html>
