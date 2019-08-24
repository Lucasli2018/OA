package cn.itcast.oa.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.Resource;

import cn.itcast.oa.service.IBookService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 通用父类Action
 * @author zhaoqx
 *
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{
	
	@Resource
	protected IBookService bookService;
	
	//在构造方法中获得model类型
	public BaseAction(){
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();//父类Action类型
		Type[] types = type.getActualTypeArguments();
		Class<T> clazz = (Class<T>) types[0];
		try {
			model = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	protected T model;

	public T getModel() {
		return model;
	}
}
