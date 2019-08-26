package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.utils.HQLHelper;

public interface IForumService {

	public List<Forum> findAll();

	public Forum getById(Long id);

	public PageBean getPageBean(HQLHelper hh, int currentPage);

}
