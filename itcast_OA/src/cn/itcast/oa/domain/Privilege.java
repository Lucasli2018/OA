package cn.itcast.oa.domain;

import java.util.HashSet;
import java.util.Set;
/**
 * 权限实体
 * @author zhaoqx
 *
 */
public class Privilege {
	private Long id;
	private String name;//权限的名称
	private String url;//权限对应的请求URL
	private Privilege parent;//上级权限
	private Set<Privilege> children = new HashSet<Privilege>();//下级权限
	private Set<Role> roles = new HashSet<Role>();//当前权限对应的角色集合
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Privilege getParent() {
		return parent;
	}
	public void setParent(Privilege parent) {
		this.parent = parent;
	}
	public Set<Privilege> getChildren() {
		return children;
	}
	public void setChildren(Set<Privilege> children) {
		this.children = children;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
