package cn.itcast.oa.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.springframework.transaction.annotation.Transactional;


import org.springframework.stereotype.Service;

import cn.itcast.oa.service.IProcessDefinitionService;

/**
 * 流程定义管理
 * @author zhaoqx
 *
 */
@Service
@Transactional
public class ProcessDefinitionServiceImpl implements IProcessDefinitionService{
	@Resource
	private ProcessEngine processEngine;

	/**
	 * 查询最新的流程定义列表
	 */
	public List<ProcessDefinition> findLastList() {
		//获得流程定义查询对象
		ProcessDefinitionQuery query = processEngine.getRepositoryService().createProcessDefinitionQuery();
		query.orderAsc(ProcessDefinitionQuery.PROPERTY_VERSION);
		List<ProcessDefinition> list = query.list();
		
		Map<String, ProcessDefinition> map = new HashMap<String, ProcessDefinition>();
		
		for(ProcessDefinition pd : list){
			map.put(pd.getKey(), pd);
		}
		
		return new ArrayList<ProcessDefinition>(map.values());
	}

	/**
	 * 部署流程定义
	 */
	public void deploy(File resource) {
		//获得部署对象
		NewDeployment deployment = processEngine.getRepositoryService().createDeployment();
		ZipInputStream zipInputStream = null;
		try {
			zipInputStream = new ZipInputStream(new FileInputStream(resource));
			deployment.addResourcesFromZipInputStream(zipInputStream);
			deployment.deploy();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			if(zipInputStream != null){
				try {
					zipInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 根据key值删除流程定义
	 */
	public void deleteByKey(String key) {
		//根据key查询流程定义
		ProcessDefinitionQuery query = processEngine.getRepositoryService().createProcessDefinitionQuery();
		//添加查询条件
		query.processDefinitionKey(key);
		List<ProcessDefinition> list = query.list();
		
		//循环删除
		for(ProcessDefinition pd : list){
			processEngine.getRepositoryService().deleteDeploymentCascade(pd.getDeploymentId());
		}
	}

	/**
	 * 根据流程定义的id获取此流程定义对应的图片文件输入流
	 */
	public InputStream getImageInputStream(String id) {
		ProcessDefinitionQuery query = processEngine.getRepositoryService().createProcessDefinitionQuery();
		query.processDefinitionId(id);
		ProcessDefinition pd = query.uniqueResult();
		String name = pd.getImageResourceName();
		
		InputStream inputStream = processEngine.getRepositoryService().getResourceAsStream(pd.getDeploymentId(), name);
		return inputStream;
	}
}
