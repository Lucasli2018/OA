package cn.itcast.oa.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import sun.misc.BASE64Encoder;
import cn.itcast.oa.domain.Application;
import cn.itcast.oa.domain.ApproveInfo;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.TaskView;
import cn.itcast.oa.domain.Template;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.service.IApplicationservice;
import cn.itcast.oa.service.IApproveInfoService;
import cn.itcast.oa.service.IFlowService;
import cn.itcast.oa.service.ITemplateService;
import cn.itcast.oa.utils.HQLHelper;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 流转Action
 * @author zhaoqx
 *
 */
@Controller
@Scope("prototype")
public class FlowAction extends ActionSupport {
	private Long templateId;//属性驱动，模板id
	private File resource;//用于文件上传
	private String status;//申请状态
	private int currentPage = 1;//当前页码
	private Long applicationId;//属性驱动，申请的id
	private InputStream inputStream;//用于文件下载的输入流
	private String fileName;//下载用的文件名
	private String taskId;//任务id
	private Boolean approval;//审批是否通过
	private String comment;//审批意见
	
	
	@Resource
	private ITemplateService templateService;
	@Resource
	private IFlowService flowService;
	@Resource
	private IApplicationservice applicationService;
	@Resource
	private IApproveInfoService approveInfoService;
	
	/**
	 * 起草申请（模板列表）
	 */
	public String templateList(){
		List<Template> list = templateService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		return "templateList";
	}
	
	/**
	 * 跳转到提交申请页面
	 */
	public String submitUI(){
		return "submitUI";
	}
	
	/**
	 * 提交申请
	 */
	public String submit(){
		Template template = templateService.getById(templateId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//处理上传文件
		String filePath = uploadFile(resource);
		
		//保存一个申请记录
		Application app = new Application();
		String title = template.getName() + "_" + getCurrentUser().getName() + "_" + sdf.format(new Date());
		app.setTitle(title);//申请的标题 ---{模板名称}_{申请人姓名}_{日期}
		app.setStatus(Application.STATUS_RUNNING);//申请的状态
		app.setApplyTime(new Date());//申请时间
		app.setFilePath(filePath);//文件存储路径
		app.setTemplate(template);//使用的模板
		app.setApplicant(getCurrentUser());//申请人
		
		flowService.submit(app);
		
		return "toMyApplicationList";
	}
	
	/**
	 * 我的申请查询列表
	 */
	public String myApplicationList(){
		//准备数据--模板列表
		List<Template> list = templateService.findAll();
		ActionContext.getContext().getValueStack().set("templateList", list);
		
		//查询分页数据---我的申请
		HQLHelper hh = new HQLHelper(Application.class);
		//查询当前登录人的申请记录
		hh.addWhere("o.applicant = ?", getCurrentUser());
		
		if(templateId != null){
			//按照模板检索
			hh.addWhere("o.template.id = ?", templateId);
		}
		if(status != null && status.trim().length() > 0){
			//按照申请状态检索
			hh.addWhere("o.status = ?", status);
		}
		
		//添加排序---按照申请时间降序
		hh.addOrderBy("o.applyTime", false);
		
		PageBean pb = applicationService.getPageBean(hh,currentPage);
		
		ActionContext.getContext().getValueStack().push(pb);
		
		return "myApplicationList";
	}
	
	/**
	 *  查看申请信息（下载申请文件）
	 */
	public String download(){
		inputStream = applicationService.getInputStreamById(applicationId);
		
		Application app = applicationService.getById(applicationId);
		fileName = app.getTitle() + ".doc";
		String agent = ServletActionContext.getRequest().getHeader("user-agent");
		try {
			fileName = this.encodeDownloadFilename(fileName, agent);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "download";
	}
	
	/**
	 * 查看流转记录（审批信息）
	 */
	public String historyApprovedList(){
		//根据申请的id查询对应的审批列表
		List<ApproveInfo> list = approveInfoService.findApproveInfoListByApplicationId(applicationId);
		
		ActionContext.getContext().getValueStack().set("list", list);
		
		return "historyApprovedList";
	}
	
	/**
	 * 待我审批（我的任务列表）
	 */
	public String myTaskList(){
		List<TaskView> list = flowService.findTaskList(getCurrentUser());
		ActionContext.getContext().getValueStack().set("list",list);
		return "myTaskList";
	}
	
	/**
	 * 跳转到审批页面
	 */
	public String approveUI(){
		return "approveUI";
	}
	
	/**
	 * 审批处理
	 */
	public String approve(){
		Application application = applicationService.getById(applicationId);
		
		//保存一个审批实体
		ApproveInfo ai = new ApproveInfo();
		ai.setApplication(application);//设置当前审批关联的申请
		ai.setApproval(approval);//是否通过
		ai.setApprover(getCurrentUser());//审批人
		ai.setApproveTime(new Date());//审批时间
		ai.setComment(getComment());//审批意见
		
		flowService.approve(ai,taskId);
		
		return "toMyTaskList";
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public Long getTemplateId() {
		return templateId;
	}
	
	/**
	 * 上传文件 
	 * @param file
	 * @return
	 */
	public String uploadFile(File file){
		String realPath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/uploadFiles");
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
		String dateStr = sdf.format(new Date());
		dateStr = realPath + dateStr;
		File dateFile = new File(dateStr);
		
		if(!dateFile.exists()){
			dateFile.mkdirs();
		}
		
		String filePath = dateStr + UUID.randomUUID().toString() + ".doc";
		File dest = new File(filePath);
		file.renameTo(dest);
		return filePath;
	}
	
	/**
	 * 获取当前登录用户
	 */
	public User getCurrentUser(){
		return (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
	}
	
	/**
	 * 下载文件时，针对不同浏览器，进行附件名的编码
	 * @param filename 下载文件名
	 * @param agent 客户端浏览器(通过request.getHeader("user-agent")获得)
	 * @return 编码后的下载附件名
	 * @throws IOException
	 */
	public String encodeDownloadFilename(String filename, String agent) throws IOException{
		if(agent.contains("Firefox")){ // 火狐浏览器
			filename = "=?UTF-8?B?"+new BASE64Encoder().encode(filename.getBytes("utf-8"))+"?=";
		}else{ // IE及其他浏览器
			filename = URLEncoder.encode(filename,"utf-8");
		}
		return filename;
	}

	public void setResource(File resource) {
		this.resource = resource;
	}

	public File getResource() {
		return resource;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setApproval(Boolean approval) {
		this.approval = approval;
	}

	public Boolean getApproval() {
		return approval;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}
}
