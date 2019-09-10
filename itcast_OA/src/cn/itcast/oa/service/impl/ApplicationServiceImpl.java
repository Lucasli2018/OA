package cn.itcast.oa.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.dao.IApplicationDao;
import cn.itcast.oa.domain.Application;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.service.IApplicationservice;
import cn.itcast.oa.utils.HQLHelper;

/**
 * 申请实体操作
 * @author zhaoqx
 *
 */
@Service
@Transactional
public class ApplicationServiceImpl implements IApplicationservice{
	@Resource
	private IApplicationDao applicationDao;
	
	public PageBean getPageBean(HQLHelper hh, int currentPage) {
		return applicationDao.getPageBean(hh, currentPage);
	}

	/**
	 * 根据申请的id获取当前申请对应的文件输入流
	 */
	public InputStream getInputStreamById(Long applicationId) {
		Application application = applicationDao.getById(applicationId);
		String filePath = application.getFilePath();
		InputStream in = null;
		try {
			in = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return in;
	}

	public Application getById(Long applicationId) {
		return applicationDao.getById(applicationId);
	}

}
