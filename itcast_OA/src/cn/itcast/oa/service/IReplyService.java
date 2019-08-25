package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;

public interface IReplyService {

	public void save(Reply model);

	public List<Reply> getReplyByTopic(Topic model);

}
