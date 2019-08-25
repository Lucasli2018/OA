package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.domain.User;

public interface IUserService {

	public List<User> findAll();

	public void delete(User model);

	public void save(User model);

	public User getById(Long id);

	public void update(User user);

	public int findByLoginName(String loginName);

	public User login(User model);

}
