package cn.itcast.oa.dao;

import java.util.List;

import cn.itcast.oa.base.IBaseDao;
import cn.itcast.oa.domain.Department;

public interface IDepartmentDao extends IBaseDao<Department>{

	public List<Department> findTopList();

	public List<Department> findChildren(Long parentId);

}
