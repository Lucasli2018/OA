package cn.itcast.oa.base;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import cn.itcast.oa.domain.Book;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.utils.HQLHelper;
/**
 * 通用Dao实现
 * @author zhaoqx
 *
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements IBaseDao<T> {
	@Resource
	private SessionFactory sessionFactory;
	
	private Class<T> clazz;
	
	public BaseDaoImpl() {
		//获得实体类型
		ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();//获得真正的父类
		Type[] types = genericSuperclass.getActualTypeArguments();
		clazz = (Class<T>) types[0];
	}
	
	public void save(T entity) {
		getSession().save(entity);
	}
	
	public void delete(Long id) {
		getSession().delete(getSession().get(clazz, id));
	}
	
	public void update(T entity) {
		getSession().update(entity);
	}

	public List<T> findAll() {
		String hql = "FROM " + clazz.getSimpleName();
		return getSession().createQuery(hql).list();
	}

	public T getById(Long id) {
		return (T) getSession().get(clazz, id);
	}
	
	public List<T> getByIds(Long[] ids) {
		String hql = "FROM " + clazz.getSimpleName() + " WHERE id in (:ids)";
		Query query = getSession().createQuery(hql);
		query.setParameterList("ids", ids);//一次赋值多个
		return query.list();
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 公共分页
	 */
	public PageBean getPageBean(HQLHelper hh, int currentPage) {
		int pageSize = getPageSize();
		int firstResult = (currentPage - 1) * pageSize;
		String listHQL = hh.getListHQL();
		String countHQL = hh.getCountHQL();
		List<Object> args = hh.getArgs();
		
		Query query = this.getSession().createQuery(listHQL);
		if(args != null && args.size() > 0){
			int index = 0;
			for(Object o : args){
				query.setParameter(index++, o);
			}
		}
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		List recordList = query.list();
		
		query = this.getSession().createQuery(countHQL);
		if(args != null && args.size() > 0){
			int index = 0;
			for(Object o : args){
				query.setParameter(index++, o);
			}
		}
		Long recordCount = (Long) query.uniqueResult();
		
		return new PageBean(currentPage, pageSize, recordCount.intValue(), recordList);
	}

	/**
	 * 读取配置文件，获取pageSize
	 * @return
	 */
	private int getPageSize() {
		int pageSize = 10;
		Properties pro = new Properties();
		
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("page.properties");
		try {
			pro.load(in);
			
			String str = (String) pro.get("pageSize");
			pageSize = Integer.parseInt(str);
		} catch (IOException e) {
			pageSize = 10;
			e.printStackTrace();
		}finally{
			try {
				if(in != null){
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return pageSize;
	}
}
