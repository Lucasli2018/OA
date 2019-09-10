package cn.itcast.oa.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 辅助生成HQL语句的工具类
 * @author zhaoqx
 *
 */
public class HQLHelper {
	private String fromStr;//FROM 子句
	private String whereStr = "";//WHERE 子句
	private String orderByStr = "";//ORDER BY 子句
	private List<Object> args = new ArrayList<Object>();//封装HQL中对应的参数信息
	
	public HQLHelper() {}
	
	/**
	 * 通过构造方法拼接FROM 子句
	 * @param clazz
	 */
	public HQLHelper(Class clazz) {
		this.fromStr = "FROM " + clazz.getSimpleName() + " o ";
	}
	
	/**
	 * 拼接WHERE 子句
	 * @param condition
	 * @param args
	 */
	public void addWhere(String condition,Object...args){//o.name = ?
		if(this.whereStr.length()==0){
			//第一次拼接WHERE子句
			this.whereStr = " WHERE " + condition;
		}else{
			//不是第一次拼接WHERE子句
			this.whereStr += " AND " + condition;
		}
		if(args != null && args.length > 0){
			//封装参数
			for(Object o : args){
				this.args.add(o);
			}
		}
	}
	
	/**
	 * 拼接ORDER BY 子句
	 * @param orderBy
	 * @param asc
	 */
	public void addOrderBy(String orderBy , boolean asc){
		if(this.orderByStr.length() == 0){
			//第一次拼接ORDER BY 子句
			this.orderByStr = " ORDER BY " + orderBy + (asc ? " ASC " : " DESC ");
		}else{
			//不是第一次拼接ORDER BY 子句
			this.orderByStr += ", " + orderBy + (asc ? " ASC " : " DESC ");
		}
	}
	
	/**
	 * 获取查询List集合的HQL语句
	 * @return
	 */
	public String getListHQL(){
		return this.fromStr + this.whereStr + this.orderByStr;
	}
	
	/**
	 * 获取查询统计记录数的HQL
	 * @param args
	 */
	public String getCountHQL(){
		return "SELECT COUNT(*) " + this.fromStr + this.whereStr;
	}

	public void setArgs(List<Object> args) {
		this.args = args;
	}

	public List<Object> getArgs() {
		return args;
	}
}
