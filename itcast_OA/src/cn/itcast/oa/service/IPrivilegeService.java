package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.domain.Role;

public interface IPrivilegeService {

	public List<Privilege> findAll();

	public List<Privilege> getByIds(Long[] privilegeIds);

	public List<Privilege> findTopList();

	public List<String> findAllUrl();

}
