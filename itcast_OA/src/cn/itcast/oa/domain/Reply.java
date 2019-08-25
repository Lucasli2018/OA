package cn.itcast.oa.domain;
/**
 * 回复实体
 * @author zhaoqx
 *
 */
public class Reply extends Article {
	private Topic topic;//当前回复属于哪个主题
	private int deleted;//删除标志 1表示已经删除 0表示没有删除
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
	
}
