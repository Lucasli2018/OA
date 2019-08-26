package cn.itcast.oa.action;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Role;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.utils.DepartmentUtils;
import cn.itcast.oa.utils.HQLHelper;
import cn.itcast.oa.utils.MD5Utils;

/**
 * 用户管理
 * @author zhaoqx
 *
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{
	
	private Long departmentId;//属性驱动，部门id
	private Long[] roleIds;//属性驱动，岗位id
	
	/**
	 * 查询用户列表
	 */
	public String list(){
		/*List<User> list = userService.findAll();
		getValueStack().set("userList", list);*/
		
		HQLHelper hh = new HQLHelper(User.class);//在构造方法中拼接FROM 语句
		
		PageBean pb = userService.getPageBean(hh,currentPage);
		getValueStack().push(pb);
		return "list";
	}
	
	/**
	 * 根据id删除用户
	 */
	public String delete(){
		userService.delete(model);
		return "toList";
	}
	
	/**
	 * 跳转到用户添加页面
	 */
	public String addUI(){
		//准备数据（部门树形列表，岗位列表）
		List<Department> topList = departmentService.findTopList();
		List<Department> treeList = DepartmentUtils.getTreeList(topList, null);
		
		List<Role> roleList = roleService.findAll();
		
		getValueStack().set("treeList", treeList);
		getValueStack().set("roleList", roleList);
		
		return "addUI";
	}
	
	/**
	 * 添加用户
	 */
	public String add(){
		if(departmentId != null){
			Department dept = departmentService.getById(departmentId);
			model.setDepartment(dept);//用户关联部门
		}
		
		if(roleIds != null && roleIds.length > 0){
			List<Role> roleList = roleService.getByIds(roleIds);
			model.setRoles(new HashSet<Role>(roleList));
		}
		
		userService.save(model);
		
		return "toList";
	}
	
	/**
	 * 跳转到用户修改页面
	 */
	public String editUI(){
		User user = userService.getById(model.getId());
		getValueStack().push(user);
		
		//准备数据（部门树形列表，岗位列表）
		List<Department> topList = departmentService.findTopList();
		List<Department> treeList = DepartmentUtils.getTreeList(topList, null);
		
		List<Role> roleList = roleService.findAll();
		
		getValueStack().set("treeList", treeList);
		getValueStack().set("roleList", roleList);
		
		if(user.getDepartment() != null){
			//查询用户所属部门，用于回显
			departmentId = user.getDepartment().getId();
		}
		
		Set<Role> roles = user.getRoles();
		if(roles != null && roles.size() > 0){
			//获得当前修改用户的岗位id，用于回显
			int size = roles.size();
			roleIds = new Long[size];
			int c = 0;
			for(Role role : roles){
				roleIds[c++] = role.getId();
			}
		}
		
		return "editUI";
	}
	
	/**
	 * 根据id修改用户
	 */
	public String edit(){
		//先查询，再修改
		User user = userService.getById(model.getId());
		
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setPhone(model.getPhone());
		user.setEmail(model.getEmail());
		user.setDescription(model.getDescription());
		
		if(departmentId != null){
			Department dept = departmentService.getById(departmentId);
			user.setDepartment(dept);
		}else{
			user.setDepartment(null);
		}
		
		if(roleIds != null && roleIds.length > 0){
			List<Role> roles = roleService.getByIds(roleIds);
			user.setRoles(new HashSet<Role>(roles));
		}else{
			user.setRoles(null);
		}
		
		userService.update(user);
		
		return "toList";
	}

	/**
	 * 初始化密码
	 */
	public String intiPassword(){
		//先查询
		User user = userService.getById(model.getId());
		user.setPassword(MD5Utils.md5("1234"));//设置密码为1234
		
		userService.update(user);
		return "toList";
	}
	
	/**
	 * 查询当前登录名是否存在
	 * @return
	 */
	public String findByLoginName(){
		String loginName = model.getLoginName();
		int count = userService.findByLoginName(loginName);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		String flag = "1";
		if(count > 0){
			//当前登录名已经存在，不能使用
			flag = "0";
		}
		try {
			ServletActionContext.getResponse().getWriter().print(flag);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	/**
	 * 用户登录
	 */
	public String login(){
		User user = userService.login(model);
		
		if(user != null){
			//登录成功
			//将登录用户放入Session
			ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
			return "home";
		}else{
			//登录失败
			//设置错误提示
			this.addActionError("用户名或者密码错误！");
			return "loginUI";
		}
	}
	
	/**
	 * 用户退出系统
	 */
	public String logout(){
		//从Session中清除登录用户
		ServletActionContext.getRequest().getSession().removeAttribute("loginUser");
		
		//跳转到登录页面
		return "loginUI";
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}
}
