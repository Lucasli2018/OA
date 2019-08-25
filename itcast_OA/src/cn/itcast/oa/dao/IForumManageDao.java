package cn.itcast.oa.dao;

import cn.itcast.oa.base.IBaseDao;
import cn.itcast.oa.domain.Forum;

public interface IForumManageDao extends IBaseDao<Forum>{

	public void  moveUp(Long id);

	public void moveDown(Long id);

}
