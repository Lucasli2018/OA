package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.domain.Role;

public interface IRoleService {

	public List<Role> findAll();

	public void delete(Role model);

	public Role getById(Long id);

	public void update(Role role);

	public void save(Role model);

	public List<Role> getByIds(Long[] roleIds);

}
