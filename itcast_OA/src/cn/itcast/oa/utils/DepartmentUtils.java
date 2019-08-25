package cn.itcast.oa.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import cn.itcast.oa.domain.Department;

/**
 * 将部门列表处理成树形结构的工具类
 * @author zhaoqx
 *
 */
public class DepartmentUtils {

	/**
	 * @param topList  顶级部门列表
	 * @param removeId 删除部门的id
	 * @return
	 */
	public static List<Department> getTreeList(List<Department> topList,Long removeId) {
		List<Department> treeList = new ArrayList<Department>();
		
		walkTree(topList,treeList,"┣",removeId);
		
		return treeList;
	}
	
	/**
	 * 组织树形部门数据
	 */
	public static void walkTree(Collection<Department> topList,List<Department> treeList ,String prefix,Long removeId){
		for(Department d : topList){
			if(removeId != null && d.getId().equals(removeId)){
				continue;
			}
			
			Department copy = new Department();
			copy.setId(d.getId());
			copy.setName(prefix + d.getName());
			
			//顶点
			treeList.add(copy);
			//子树
			Set<Department> children = d.getChildren();
			walkTree(children,treeList,"　" + prefix,removeId);
		}
	}
}
