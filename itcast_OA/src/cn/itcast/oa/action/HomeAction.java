package cn.itcast.oa.action;

import org.springframework.stereotype.Controller;

/**
 *  主页Action
 * @author zhaoqx
 *
 */
@Controller
public class HomeAction {
	
	/**
	 * 跳转到首页面
	 */
	public String index(){
		return "index";
	}
	/**
	 * 跳转到top.jsp页面
	 */
	public String top(){
		return "top";
	}
	/**
	 * 跳转到left.jsp页面
	 */
	public String left(){
		return "left";
	}
	/**
	 * 跳转到right.jsp页面
	 */
	public String right(){
		return "right";
	}
	/**
	 * 跳转到bottom.jsp页面
	 */
	public String bottom(){
		return "bottom";
	}
}
