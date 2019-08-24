package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.domain.Book;

public interface IBookService {

	/**
	 * 添加
	 */
	public void save(Book book);
	
	/**
	 * 根据id删除
	 */
	public void delete(Long id);
	
	/**
	 * 根据id修改
	 */
	public void update(Book book);
	
	/**
	 * 根据id查询
	 */
	public Book getById(Long id);
	
	/**
	 * 一次查询多个对象
	 */
	public List<Book> getByIds(Long[] ids);
	
	/**
	 * 查询所有
	 */
	public List<Book> findAll();

}
