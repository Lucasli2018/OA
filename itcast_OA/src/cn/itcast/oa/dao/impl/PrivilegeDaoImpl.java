package cn.itcast.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.dao.IPrivilegeDao;
import cn.itcast.oa.domain.Privilege;

/**
 * 权限管理
 * @author zhaoqx
 *
 */
@Repository
@SuppressWarnings("unchecked")
public class PrivilegeDaoImpl extends BaseDaoImpl<Privilege> implements IPrivilegeDao{
	/**
	 * 查询顶级权限列表
	 */
	public List<Privilege> findTopList() {
		String hql = "FROM Privilege p WHERE p.parent IS NULL";
		return this.getSession().createQuery(hql).list();
	}

}
