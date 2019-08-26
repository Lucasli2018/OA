package cn.itcast.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.dao.IReplyDao;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.service.IReplyService;
import cn.itcast.oa.utils.HQLHelper;

/**
 * 回复操作
 * @author zhaoqx
 *
 */
@Service
@Transactional
public class ReplyServiceImpl implements IReplyService{
	@Resource
	private IReplyDao replyDao;
	
	/**
	 * 发表回复
	 */
	public void save(Reply model) {
		replyDao.save(model);
		
		Forum forum = model.getTopic().getForum();//持久对象
		forum.setArticleCount(forum.getArticleCount() + 1);//版块的文章数量加1
		
		Topic topic = model.getTopic();//持久对象
		topic.setLastUpdateTime(model.getPostTime());//回复对应的主题的最后更新时间为回复时间
		topic.setLastReply(model);//主题的最后一个回复为当前回复
		topic.setReplyCount(topic.getReplyCount() + 1);//回复数量加1
	}

	/**
	 * 根据主题查询对应的回复列表
	 */
	public List<Reply> getReplyByTopic(Topic model) {
		return replyDao.getReplyByTopic(model);
	}

	/**
	 * 分页查询
	 */
	public PageBean getPageBean(int currentPage, Topic model) {
		return replyDao.getPageBean(currentPage,model);
	}

	/**
	 * 分页查询 
	 */
	public PageBean getPageBean(HQLHelper hh, int currentPage) {
		return replyDao.getPageBean(hh, currentPage);
	}

}
