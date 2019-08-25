package cn.itcast.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;


import org.springframework.stereotype.Service;

import cn.itcast.oa.dao.ITopicDao;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.service.ITopicService;

/**
 * 主题操作
 * @author zhaoqx
 *
 */
@Service
@Transactional
public class TopicServiceImpl implements ITopicService{
	@Resource
	private ITopicDao topicDao;
	public List<Topic> findTopicByForum(Forum model) {
		return topicDao.findTopicByForum(model);
	}

}
