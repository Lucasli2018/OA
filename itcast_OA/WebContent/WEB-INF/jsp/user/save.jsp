<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
<head>
	<title>岗位设置</title>
	<%@include file="/WEB-INF/jsp/public/header.jsp" %>
	<script type="text/javascript">
		$(function(){
			//为loginName绑定离焦事件
			$("#loginName").blur(function(){
				var value = $(this).val();
				if(value != null && value.trim().length > 0){
					//发送Ajax，验证当前输入的登录名是否可用
					var url = "${pageContext.request.contextPath}/user_findByLoginName.do";
					$.post(url,{'loginName':$(this).val()},function(data){
						if(data == '0'){
							//当前登录名已经存在，不能使用
							$("#showMsg").html('<font color="red">当前登录名已经存在，不能使用</font>');
						}else{
							$("#showMsg").html('<font color="green">当前登录名不存在，可以使用</font>');
						}
					});
				}else{
					$("#showMsg").html('');
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 用户信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <s:form action="user_%{id==null?'add':'edit'}" namespace="/" method="post">
    	<s:hidden name="id"></s:hidden>
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 用户信息 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr><td width="100">所属部门</td>
                        <td>
                        	<s:select list="treeList" name="departmentId" listKey="id" listValue="name" headerKey="" headerValue="请选择部门" cssClass="SelectStyle"></s:select>
                        </td>
                    </tr>
                    <tr><td>登录名</td>
                        <td>
                        	<s:textfield id="loginName" name="loginName" cssClass="InputStyle"></s:textfield>
                        	*
							（登录名要唯一）
							<div id="showMsg"></div>
						</td>
                    </tr>
                    <tr><td>姓名</td>
                        <td>
                        	<s:textfield name="name" cssClass="InputStyle"></s:textfield>
                        *</td>
                    </tr>
					<tr><td>性别</td>
                        <td>
                        	<s:radio name="gender" list="#{'1':'男','0':'女'}"></s:radio>
						</td>
                    </tr>
					<tr><td>联系电话</td>
                        <td>
                        	<s:textfield name="phone" cssClass="InputStyle"></s:textfield>
                       </td>
                    </tr>
                    <tr><td>E-mail</td>
                        <td>
                        	<s:textfield name="email" cssClass="InputStyle"></s:textfield>
                        </td>
                    </tr>
                    <tr><td>备注</td>
                        <td>
                        	<s:textarea name="description" cssClass="TextareaStyle"></s:textarea>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        
		<div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 岗位设置 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
						<td width="100">岗位</td>
                        <td>
                        	<s:select name="roleIds" list="roleList" listKey="id" listValue="name" multiple="true" size="10" cssClass="SelectStyle"></s:select>
                           	 按住Ctrl键可以多选或取消选择
                        </td>
                    </tr>
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
</body>
</html>

