package cn.itcast.oa.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import sun.misc.BASE64Encoder;
import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Template;

/**
 * 模板管理
 * @author zhaoqx
 *
 */
@Controller
@Scope("prototype")
public class TemplateAction extends BaseAction<Template>{
	private File resource;//用于文件上传
	private InputStream downloadFile;//用于文件下载的输入流
	private String fileName;//下载时的文件名
	
	/**
	 * 查询模板列表
	 */
	public String list(){
		List<Template> list = templateService.findAll();
		getValueStack().set("list", list);
		return "list";
	}
	
	/**
	 * 根据id删除模板
	 */
	public String delete(){
		templateService.delete(model.getId());
		
		return "toList";
	}
	
	/**
	 * 跳转到修改页面
	 */
	public String editUI(){
		//根据id查询模板对象用于页面回显
		Template template = templateService.getById(model.getId());
		getValueStack().push(template);
		
		//查询流程定义列表,用于填充所用流程下拉列表
		List<ProcessDefinition> pdList = processDefinitionService.findLastList();
		getValueStack().set("pdList", pdList);
		return "editUI";
	}
	
	/**
	 * 修改模板
	 */
	public String edit(){
		//先查询，再修改
		Template template = templateService.getById(model.getId());
		template.setName(model.getName());
		template.setProcessDefinitionKey(model.getProcessDefinitionKey());
		
		if(resource != null){
			//用户上传了新文件
			String filePath = uploadFile(resource);//上传文件，并且返回上传的文件路径
			
			//删除原来的文件
			String path = template.getFilePath();
			File file = new File(path);
			if(file.exists()){
				file.delete();
			}
			template.setFilePath(filePath);//重新设置新文件路径
		}
		
		templateService.update(template);
		
		return "toList";
	}

	/**
	 * 跳转到添加模板页面
	 */
	public String addUI(){
		//准备数据----流程定义列表
		List<ProcessDefinition> pdList = processDefinitionService.findLastList();
		getValueStack().set("pdList", pdList);
		
		return "addUI";
	}
	
	/**
	 * 添加模板
	 */
	public String add(){
		//将上传的文件保存在uploadFiles目录中
		String filePath = uploadFile(resource);
		model.setFilePath(filePath);
		templateService.save(model);
		return "toList";
	}
	
	/**
	 * 下载模板对应的文件
	 */
	public String download(){
		downloadFile = templateService.getInputStreamById(model.getId());
		
		Template template = templateService.getById(model.getId());
		
		String agent = ServletActionContext.getRequest().getHeader("user-agent");
		try {
			fileName = encodeDownloadFilename(template.getName() + ".doc", agent);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "download";
	}

	public void setResource(File resource) {
		this.resource = resource;
	}

	public File getResource() {
		return resource;
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
		resource.renameTo(dest);
		return filePath;
	}

	public void setDownloadFile(InputStream downloadFile) {
		this.downloadFile = downloadFile;
	}

	public InputStream getDownloadFile() {
		return downloadFile;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
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
}
