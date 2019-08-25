<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>导航菜单</title>
	<%@include file="/WEB-INF/jsp/public/header.jsp" %>
<script language="JavaScript" src="script/menu.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/style/blue/menu.css" />
</head>
<body style="margin: 0">
<div id="Menu">
    <ul id="MenuUl">
        <li class="level1">
            <div onClick="menuClick(this);" class="level1Style"><img src="${pageContext.request.contextPath }/style/images/MenuIcon/FUNC20082.gif" class="Icon" /> 系统管理</div>
            <ul style="display: none;" class="MenuLevel2">
                <li class="level2">
                    <div class="level2Style"><img src="${pageContext.request.contextPath }/style/images/MenuIcon/menu_arrow_single.gif" /> <s:a target="right" action="role_list" namespace="/"> 岗位管理</s:a></div>
                </li>
                <li class="level2">
                    <div class="level2Style"><img src="${pageContext.request.contextPath }/style/images/MenuIcon/menu_arrow_single.gif" /> <s:a target="right" action="department_list" namespace="/"> 部门管理</s:a></div>
                </li>
                <li class="level2">
                    <div class="level2Style"><img src="${pageContext.request.contextPath }/style/images/MenuIcon/menu_arrow_single.gif" /> <s:a target="right" action="user_list" namespace="/"> 用户管理</s:a></div>
                </li>
            </ul>
        </li>
    </ul>
</div>
</body>
</html>
