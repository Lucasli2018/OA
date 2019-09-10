package cn.itcast.oa.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 申请实体
 * @author zhaoqx
 *
 */
public class Application {
	public static final String STATUS_RUNNING = "审批中";
	public static final String STATUS_APPROVED = "已通过";
	public static final String STATUS_UNAPPROVED = "未通过";
	
	private Long id;
	private String title;//申请的标题
	private User applicant;//申请人
	private Date applyTime;//申请时间
	private String status;//申请的状态
	private String filePath;//此申请对应的文件存储路径
	private Template template;//此申请使用的膜拜
	private Set<ApproveInfo> approveInfos = new HashSet<ApproveInfo>();// 审批集合
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public User getApplicant() {
		return applicant;
	}
	public void setApplicant(User applicant) {
		this.applicant = applicant;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Template getTemplate() {
		return template;
	}
	public void setTemplate(Template template) {
		this.template = template;
	}
	public Set<ApproveInfo> getApproveInfos() {
		return approveInfos;
	}
	public void setApproveInfos(Set<ApproveInfo> approveInfos) {
		this.approveInfos = approveInfos;
	}
	
	
}
