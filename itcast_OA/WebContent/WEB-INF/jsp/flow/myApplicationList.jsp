<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>我的申请查询</title>
    <%@include file="/WEB-INF/jsp/public/header.jsp" %>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 我的申请查询
        </div>
        <div id="Title_End"></div>
    </div>
</div>


<div id="QueryArea">
	<div style="height: 30px">
		<s:form id="pageForm" action="flow_myApplicationList" namespace="/" method="post">
		<table border=0 cellspacing=3 cellpadding=5>
			<tr>
				<td>按条件查询：</td>
				<td>
					<s:select cssClass="SelectStyle" name="templateId" list="templateList" listKey="id" listValue="name" headerKey="" headerValue="查看全部模板"></s:select>
				</td>
				<td>
					<!-- 使用OGNL构造一个list集合 -->
					<s:select cssClass="SelectStyle" name="status" list="%{    {'审批中','未通过','已通过'}   }" headerKey="" headerValue="查看全部状态"></s:select>
				</td>
				<td><a href=""><input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG"/></a></td>
			</tr>
		</table>
		</s:form>
	</div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
				<td width="250px">标题</td>
				<td width="115px">申请人</td>
				<td width="115px">申请日期</td>
				<td width="115px">当前状态</td>
				<td>相关操作</td>
			</tr>
		</thead>	
				
		<!--显示数据列表：被退回的我的表的单显示示例
		<tbody id="TableData" class="dataContainer" datakey="formList">
			-->
			<!-- 被退回的我的表的单显示示例 
			<tr class="TableDetail1 template">
				<td><a href="${pageContext.request.contextPath}/Flow_Formflow/showForm.html">${form.title}</a></td>
				<td>${form.applicant.name}&nbsp;</td>
				<td>${form.applyTime}&nbsp;</td>
				<td>审批中&nbsp;</td>
				<td><a href="${pageContext.request.contextPath}/Flow_Formflow/showForm.html">查看申请信息</a>
					<a href="${pageContext.request.contextPath}/Flow_Formflow/approvedHistory.html">查看流转记录</a>
					<a href="${pageContext.request.contextPath}/Flow_Formflow/editAndResubmitUI.html">修改后再次提交</a>
					<a href="#" onClick="return delConfirm()">删除</a>
				</td>
			</tr>
		</tbody>
		-->

		<!--显示数据列表：正在审批或审批完成的表单显示示例-->
        <tbody id="TableData" class="dataContainer" datakey="formList">
			<!-- 正在审批或审批完成的表单显示示例 -->
			<s:iterator value="recordList">
				<tr class="TableDetail1 template">
					<td>${title}</td>
					<td>${applicant.name}&nbsp;</td>
					<td>
						<s:date name="applyTime" format="yyyy-MM-dd"/>
						&nbsp;
					</td>
					<td>${status}&nbsp;</td>
					<td><s:a  action="flow_download?applicationId=%{id}">查看申请信息</s:a>
						<s:a action="flow_historyApprovedList?applicationId=%{id}">
							查看流转记录
						</s:a>
					</td>
				</tr>
			</s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail"><div id="TableTail_inside"></div></div>
</div>

<!--分页信息-->
<%@include file="/WEB-INF/jsp/public/pageView.jsp" %>
<div class="Description">
	说明：<br />
	<!--
	1，对于退回的表单，可以执行“修改后再次提交”与“删除”的操作。<br />&nbsp;&nbsp;
	   其他状态表单（正在审批的或是审批完成的）则没有这两个操作。<br />
	   2，删除退回的申请文档后，此文档相关的审批信息也要同时删除，对应的流程也终止了。<br />
	   -->
</div>

</body>
</html>
