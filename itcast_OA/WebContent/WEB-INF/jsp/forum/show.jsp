<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
<head>
	<title>【${name }】中的主题列表</title>
	<%@include file="/WEB-INF/jsp/public/header.jsp" %>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 【${name }】中的主题列表
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
	<div id="PageHead"></div>
	<center>
		<div class="ItemBlock_Title1" style="width: 98%;">
			<font class="MenuPoint"> &gt; </font>
			<s:a action="forum_list" namespace="/">论坛</s:a>
			<font class="MenuPoint"> &gt; </font>
			${name }
			<span style="margin-left:30px;">
				<s:a namespace="/" action="topic_addUI?forumId=%{id}">
					<img align="absmiddle" src="${pageContext.request.contextPath}/style/blue/images/button/publishNewTopic.png"/>
				</s:a>
			</span>
		</div>
		
		<div class="ForumPageTableBorder">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<!--表头-->
				<tr align="center" valign="middle">
					<td width="3" class="ForumPageTableTitleLeft">
						<img border="0" width="1" height="1" src="${pageContext.request.contextPath}/style/images/blank.gif" />
					</td>
					<td width="50" class="ForumPageTableTitle"><!--状态/图标-->&nbsp;</td>
					<td class="ForumPageTableTitle">主题</td>
					<td width="130" class="ForumPageTableTitle">作者</td>
					<td width="100" class="ForumPageTableTitle">回复数</td>
					<td width="130" class="ForumPageTableTitle">最后回复</td>
					<td width="3" class="ForumPageTableTitleRight">
						<img border="0" width="1" height="1" src="${pageContext.request.contextPath}/style/images/blank.gif" />
					</td>
				</tr>
				<tr height="1" class="ForumPageTableTitleLine"><td colspan="8"></td></tr>
				<tr height=3><td colspan=8></td></tr>
					
				<!--主题列表-->
				<tbody class="dataContainer" datakey="topicList">
					<s:iterator value="recordList">
						<tr height="35" id="d0" class="template">
							<td></td>
							<td class="ForumTopicPageDataLine" align="center">
								<img src="${pageContext.request.contextPath}/style/images/topicType_${type}.gif" />
							</td>
							<td class="Topic">
							<s:a cssClass="Default" action="topic_show?id=%{id}">
								${title}
							</s:a></td>
							<td class="ForumTopicPageDataLine">
								<ul class="ForumPageTopicUl">
									<li class="Author">${author.name }</li>
									<li class="CreateTime">
										<s:date name="postTime" format="yyyy-MM-dd HH:mm:ss"/>
									</li>
								</ul>
							</td>
							<td class="ForumTopicPageDataLine Reply" align="center"><b>${replyCount}</b></td>
							<td class="ForumTopicPageDataLine">
								<ul class="ForumPageTopicUl">
									<li class="Author">
										${lastReply.author.name }
									</li>
									<li class="CreateTime">
										<s:date name="lastReply.postTime" format="yyyy-MM-dd HH:mm:ss"/>
									</li>
								</ul>
							</td>
							<td></td>
						</tr>
					</s:iterator>
				</tbody>
					<!--主题列表结束-->	
						
					<tr height="3"><td colspan="9"></td></tr>
				
			</table>
			
			<s:form id="pageForm" action="forum_show" namespace="/">
				<s:hidden name="id"></s:hidden>
				<!--其他操作-->
				<div id="TableTail">
					<div id="TableTail_inside">
						<table border="0" cellspacing="0" cellpadding="0" height="100%" align="left">
							<tr valign=bottom>
								<td></td>
								<td>
									<select id="viewType" name="viewType">
										<option value="0">全部主题</option>
										<option value="1">全部精华贴</option>
									</select>
									
									<select id="orderBy" name="orderBy">
										<option value="0">默认排序（按最后更新时间排序，但所有置顶帖都在前面）</option>
										<option value="1">按最后更新时间排序</option>
										<option value="2">按主题发表时间排序</option>
										<option value="3">按回复数量排序</option>
									</select>
									
									<select id="asc" name="asc">
										<option value="false">降序</option>
										<option value="true">升序</option>
									</select>
									
									<script type="text/javascript">
										$("#viewType").val('${viewType }');
										$("#orderBy").val('${orderBy }');
										$("#asc").val('${asc }');
									</script>
									<input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" align="ABSMIDDLE"/>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</s:form>
		</div>
	</center>
</div>

<!--分页信息-->
<%@include file="/WEB-INF/jsp/public/pageView.jsp" %>



</body>
</html>
