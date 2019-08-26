package cn.itcast.test;

import java.util.List;

import cn.itcast.oa.domain.User;
import cn.itcast.oa.utils.HQLHelper;

public class HQLHelperTest {
	/***
	 * 
	 * from User o 
		where o.name = ? and o.age > ? and o.address = ? and o.tel is not null
		order by o.name desc       ,o.age asc
	 * 
	 * @param args
	 */
	public static void main(String[] aaa) {
		HQLHelper hh = new HQLHelper(User.class);
		
		hh.addWhere("o.name = ?", "zhangsan");
		hh.addWhere("o.age > ?", 20);
		hh.addWhere("o.address = ?", "bj");
		hh.addWhere("o.tel is not null");
		
		hh.addOrderBy("o.name", false);
		hh.addOrderBy("o.age", true);
		
		String listHQL = hh.getListHQL();
		String countHQL = hh.getCountHQL();
		List<Object> args = hh.getArgs();
		
		System.out.println(listHQL);
		System.out.println(countHQL);
		System.out.println(args);
	}
}
