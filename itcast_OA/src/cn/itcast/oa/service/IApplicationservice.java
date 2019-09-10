package cn.itcast.oa.service;

import java.io.InputStream;

import cn.itcast.oa.domain.Application;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.utils.HQLHelper;

public interface IApplicationservice {

	public PageBean getPageBean(HQLHelper hh, int currentPage);

	public InputStream getInputStreamById(Long applicationId);

	public Application getById(Long applicationId);

}
