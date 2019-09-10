package cn.itcast.oa.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.dao.IApproveInfoDao;
import cn.itcast.oa.domain.ApproveInfo;
/**
 * 审批操作
 * @author zhaoqx
 *
 */

@Repository
public class ApproveInfoDaoImpl extends BaseDaoImpl<ApproveInfo> implements IApproveInfoDao{

	/**
	 * 根据申请的id查询审批集合
	 */
	public List<ApproveInfo> findApproveInfoListByApplicationId(
			Long applicationId) {
		String hql = "FROM ApproveInfo ai WHERE ai.application.id = ? ORDER BY ai.approveTime ASC";
		Query query = this.getSession().createQuery(hql);
		query.setParameter(0, applicationId);
		
		return query.list();
	}

}
 