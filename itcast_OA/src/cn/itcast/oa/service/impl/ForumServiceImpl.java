package cn.itcast.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;


import org.springframework.stereotype.Service;

import cn.itcast.oa.dao.IForumDao;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.service.IForumService;

/**
 * 参与版块操作
 * @author zhaoqx
 *
 */
@Service
@Transactional
public class ForumServiceImpl implements IForumService{
	@Resource
	private IForumDao forumDao;
	public List<Forum> findAll() {
		return forumDao.findAll();
	}
	public Forum getById(Long id) {
		return forumDao.getById(id);
	}

}
