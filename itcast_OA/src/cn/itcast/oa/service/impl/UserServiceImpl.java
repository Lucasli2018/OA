package cn.itcast.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import cn.itcast.oa.dao.IUserDao;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.service.IUserService;
/**
 * 用户管理
 * @author zhaoqx
 *
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {
	@Resource
	private IUserDao userDao;

	/**
	 * 查询所有用户列表
	 */
	public List<User> findAll() {
		return userDao.findAll();
	}

	/**
	 * 根据id删除用户
	 */
	public void delete(User model) {
		userDao.delete(model.getId());
	}

	public void save(User model) {
		userDao.save(model);
	}

	/**
	 * 根据id查询用户
	 */
	public User getById(Long id) {
		return userDao.getById(id);
	}

	/**
	 * 根据id修改用户
	 */
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public int findByLoginName(String loginName) {
		return userDao.findByLoginName(loginName);
	}
}
