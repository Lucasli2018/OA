package cn.itcast.oa.domain;

import java.util.Date;

/**
 * 审批实体
 * @author zhaoqx
 *
 */
public class ApproveInfo {
	private Long id;
	private User approver;//审批人
	private Date approveTime;//审批时间
	private Boolean approval;//是否通过
	private String comment;//审批意见
	private Application application;//申请
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getApprover() {
		return approver;
	}
	public void setApprover(User approver) {
		this.approver = approver;
	}
	public Date getApproveTime() {
		return approveTime;
	}
	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}
	public Boolean getApproval() {
		return approval;
	}
	public void setApproval(Boolean approval) {
		this.approval = approval;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
}
