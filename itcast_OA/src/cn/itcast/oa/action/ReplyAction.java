package cn.itcast.oa.action;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;

/**
 * 回复操作
 * @author zhaoqx
 *
 */
@Controller
@Scope("prototype")
public class ReplyAction extends BaseAction<Reply>{
	private Long topicId;//属性驱动，主题id
	
	/**
	 * 跳转到回复页面
	 */
	public String addUI(){
		Topic topic = topicService.getById(topicId);
		getValueStack().push(topic);
		return "addUI";
	}
	
	/**
	 * 发表回复
	 */
	public String add(){
		Topic topic = topicService.getById(topicId);
		model.setTopic(topic);//回复关联主题
		
		model.setDeleted(0);//默认为未删除
		model.setIpAddress(getIpAddress());//设置客户端的ip地址
		model.setPostTime(new Date());//设置回复时间为当前时间
		model.setAuthor(getLoginUser());//设置回复人为当前登录用户
		
		replyService.save(model);
		return "toReplyList";//跳转到回复列表
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public Long getTopicId() {
		return topicId;
	}
}
