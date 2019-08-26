package cn.itcast.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.dao.IForumManageDao;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.service.IForumManageService;
import cn.itcast.oa.utils.HQLHelper;

/**
 * 版块管理
 * @author zhaoqx
 *
 */
@Service
@Transactional
public class ForumManageServiceImpl implements IForumManageService{
	@Resource
	private IForumManageDao forumManageDao;
	/**
	 * 查询版块列表
	 */
	public List<Forum> findAll() {
		return forumManageDao.findAll();
	}
	
	/**
	 * 根据id删除版块
	 */
	public void delete(Forum model) {
		forumManageDao.delete(model.getId());
	}

	/**
	 * 保存版块
	 */
	public void save(Forum model) {
		forumManageDao.save(model);
	}

	/**
	 * 根据id查询板块
	 */
	public Forum getById(Long id) {
		return forumManageDao.getById(id);
	}

	/**
	 * 根据id修改版块信息
	 */
	public void update(Forum forum) {
		forumManageDao.update(forum);
	}

	/**
	 * 上移版块
	 */
	public void moveUp(Long id) {
		forumManageDao.moveUp(id);
	}

	/**
	 * 下移版块
	 */
	public void moveDown(Long id) {
		forumManageDao.moveDown(id);
	}

	/**
	 * 分页查询
	 */
	public PageBean getPageBean(HQLHelper hh, int currentPage) {
		return forumManageDao.getPageBean(hh, currentPage);
	}

}
