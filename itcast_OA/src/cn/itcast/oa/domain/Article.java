package cn.itcast.oa.domain;

import java.util.Date;

/**
 * 文章实体
 * @author zhaoqx
 *
 */
public class Article {
	private Long id;
	private String content;//内容
	private Date postTime;//发表时间
	private User author;//作者
	private String ipAddress;//ip地址
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}
