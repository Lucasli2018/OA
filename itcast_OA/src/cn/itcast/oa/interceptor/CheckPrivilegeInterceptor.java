package cn.itcast.oa.interceptor;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.itcast.oa.domain.User;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 进行权限检查的拦截器
 * @author zhaoqx
 *
 */
public class CheckPrivilegeInterceptor extends AbstractInterceptor{
	/**
	 * 拦截方法
	 */
	public String intercept(ActionInvocation ai) throws Exception {
		//从Session中获取登录用户
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		
		String actionName = ai.getProxy().getActionName();
		String namespace = ai.getProxy().getNamespace();
		String url = namespace + actionName;//  /user_addUI
		
		if(url.endsWith("UI")){
			//如果访问URL以UI结尾，就去除
			url = url.substring(0, url.length() - 2);
		}
		
		//System.out.println("访问URL=" + url);
		
		// 一 ，用户没有登录
		if(user == null){
			// a 如果用户访问的是登录功能,则放行
			if("/user_login".equals(url)){
				return ai.invoke();
			}else{
				// b 如果用户访问的不是登录功能，则跳转到登录页面
				return "loginUI";
			}
		}else{
			// 二， 用户已经登录
			List<String> allUrl = (List<String>) ServletActionContext.getServletContext().getAttribute("allUrl");
			//如果用户访问的是要验证的权限
			if(allUrl.contains(url)){
				boolean b = user.hasPrivilegeByUrl(url);
				if(b){
					// a 如果用户有权限，则放行
					return ai.invoke();
				}else{
					// b 如果用户没有权限，则跳转到没有权限的提示页面
					return "noPrivilegeUI";
				}
			}else{
				//如果用户访问的不是要验证的权限
				return ai.invoke();
			}
		}
	}
}
