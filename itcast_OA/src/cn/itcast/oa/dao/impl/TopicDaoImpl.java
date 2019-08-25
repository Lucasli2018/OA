package cn.itcast.oa.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.itcast.oa.dao.ITopicDao;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Topic;

import cn.itcast.oa.base.BaseDaoImpl;

/**
 * 主题操作
 * @author zhaoqx
 *
 */
@Repository
@SuppressWarnings("unchecked")
public class TopicDaoImpl extends BaseDaoImpl<Topic> implements ITopicDao{

	/**
	 * 根据版块查询主题列表
	 * select * from itcast_topic order by (case type when 2 then 2 else 1 end ) desc ,postTime desc;
	 */
	public List<Topic> findTopicByForum(Forum model) {
		String hql = "FROM Topic t WHERE t.forum = ? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 1 END) DESC,t.postTime DESC";
		Query query = this.getSession().createQuery(hql);
		query.setParameter(0, model);
		return query.list();
	}

}
