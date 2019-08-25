package cn.itcast.oa.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 版块实体
 * @author zhaoqx
 *
 */
public class Forum {
	private Long id;
	private String name;
	private String description;
	private int position;
	
	private Set<Topic> topices = new HashSet<Topic>();//当前版块中的主题集合
	private int topicCount;//当前版块中的主题数量
	private int articleCount;//当前版块中的文章数量
	private Topic lastTopic;//当前版块中的最后一个主题
	
	
	public Set<Topic> getTopices() {
		return topices;
	}
	public void setTopices(Set<Topic> topices) {
		this.topices = topices;
	}
	public int getTopicCount() {
		return topicCount;
	}
	public void setTopicCount(int topicCount) {
		this.topicCount = topicCount;
	}
	public int getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}
	public Topic getLastTopic() {
		return lastTopic;
	}
	public void setLastTopic(Topic lastTopic) {
		this.lastTopic = lastTopic;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
}
