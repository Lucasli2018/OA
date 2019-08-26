package cn.itcast.oa.action;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.utils.HQLHelper;

/**
 * 主题操作
 * @author zhaoqx
 *
 */
@Controller
@Scope("prototype")
public class TopicAction extends BaseAction<Topic>{
	private Long forumId;//属性驱动，版块id
	
	
	/**
	 * 跳转到发表主题页面
	 */
	public String addUI(){
		//根据版块id查询板块信息，用于页面显示
		Forum forum = forumService.getById(forumId);
		getValueStack().push(forum);
		return "addUI";
	}
	
	/**
	 * 发表主题
	 */
	public String add(){
		Forum forum = forumService.getById(forumId);
		model.setForum(forum);//主题关联版块
		
		model.setIpAddress(getIpAddress());//设置客户端的ip地址
		model.setPostTime(new Date());//当前主题的发布时间
		model.setLastUpdateTime(model.getPostTime());//设置最后更新时间为发表主题的时间
		model.setReplyCount(0);//设置当前主题的回复数量为0
		model.setType(0);//主题的类型为普通帖
		
		model.setAuthor(getLoginUser());//设置主题的作者为当前登录用户
		model.setLastReply(null);//默认没有回复
		
		topicService.save(model);
		
		return "toTopicList";
	}
	
	/**
	 * 显示单个主题（回复列表）
	 */
	public String show(){
		//根据id查询主题
		Topic topic = topicService.getById(model.getId());
		getValueStack().push(topic);
		
		//根据主题查询对应的回复列表
		//List<Reply> replyList = replyService.getReplyByTopic(model);
		//getValueStack().set("replyList", replyList);
		
		//PageBean pb = replyService.getPageBean(currentPage,model);
		
		HQLHelper hh = new HQLHelper(Reply.class);
		hh.addWhere("o.topic = ?", model);
		hh.addOrderBy("o.postTime", true);
		PageBean pb = replyService.getPageBean(hh,currentPage);
		getValueStack().push(pb);
		
		return "show";
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

	public Long getForumId() {
		return forumId;
	}
}
