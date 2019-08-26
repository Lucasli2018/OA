package cn.itcast.oa.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.utils.HQLHelper;

/**
 * 版块管理
 * @author zhaoqx
 *
 */
@Controller
@Scope("prototype")
public class ForumManageAction extends BaseAction<Forum>{
	/**
	 * 查询板块列表
	 */
	public String list(){
		//List<Forum> list = forumManageService.findAll();
		//getValueStack().set("list", list);
		
		HQLHelper hh = new HQLHelper(Forum.class);
		hh.addOrderBy("o.position", true);
		
		PageBean pb = forumManageService.getPageBean(hh,currentPage);
		getValueStack().push(pb);
		return "list";
	}
	
	/**
	 * 根据id删除板块
	 */
	public String delete(){
		forumManageService.delete(model);
		return "toList";
	}
	
	/**
	 * 跳转到添加板块页面
	 */
	public String addUI(){
		return "addUI";
	}
	
	/**
	 * 添加板块
	 */
	public String add(){
		forumManageService.save(model);
		return "toList";
	}
	
	/**
	 * 跳转到版块修改页面
	 */
	public String editUI(){
		Forum forum = forumManageService.getById(model.getId());
		
		getValueStack().push(forum);
		return "editUI";
	}
	
	/**
	 * 修改版块
	 */
	public String edit(){
		//先查询，再修改
		Forum forum = forumManageService.getById(model.getId());
		
		forum.setName(model.getName());
		forum.setDescription(model.getDescription());
		
		forumManageService.update(forum);
		return "toList";
	}
	
	/**
	 * 上移
	 */
	public String moveUp(){
		forumManageService.moveUp(model.getId());
		
		return "toList";
	}

	/**
	 * 下移
	 */
	public String moveDown(){
		forumManageService.moveDown(model.getId());
		return "toList";
	}
}
