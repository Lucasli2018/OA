package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.domain.Department;

public interface IDepartmentService {

	public List<Department> findAll();

	public void delete(Department model);

	public Department getById(Long parentId);

	public void save(Department model);

	public void update(Department dept);

	public List<Department> findTopList();

	public List<Department> findChildren(Long parentId);

}
