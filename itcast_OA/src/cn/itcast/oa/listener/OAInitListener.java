package cn.itcast.oa.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.service.IPrivilegeService;
/**
 * 项目启动时加载权限数据的监听器
 * @author zhaoqx
 *
 */
public class OAInitListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
	}

	/**
	 * 初始化方法
	 */
	public void contextInitialized(ServletContextEvent sce) {
		// 1 获取spring容器
		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		
		// 2从spring容器中获取privilegeService
		IPrivilegeService service = (IPrivilegeService) applicationContext.getBean("privilegeServiceImpl");
		
		// 3使用service查询权限数据
		List<Privilege> topList = service.findTopList();
		
		// 4将权限数据放入application作用域
		sce.getServletContext().setAttribute("privilegeTopList", topList);
		
		System.out.println("权限数据已经放入application作用域了");
		
		//查询所有要进行校验的权限URL
		List<String> allUrl = service.findAllUrl();
		sce.getServletContext().setAttribute("allUrl", allUrl);
	}

}
