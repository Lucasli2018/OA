package cn.itcast.oa.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.utils.HQLHelper;

/**
 * 参与版块操作
 * @author zhaoqx
 *
 */
@Controller
@Scope("prototype")
public class ForumAction extends BaseAction<Forum>{
	
	/**
	 * <option value="0">全部主题</option>
	 * <option value="1">全部精华贴</option>
	 */
	private int viewType;//属性驱动，显示哪些主题
	
	/**
	 *  <option value="0">默认排序（按最后更新时间排序，但所有置顶帖都在前面）</option>
		<option value="1">按最后更新时间排序</option>
		<option value="2">按主题发表时间排序</option>
		<option value="3">按回复数量排序</option>
	 */
	private int orderBy;//属性驱动，排序字段
	
	/**
	 * <option value="false">降序</option>
	   <option value="true">升序</option>
	 */
	private boolean asc;//属性驱动，升序或者降序
	
	public int getViewType() {
		return viewType;
	}

	public void setViewType(int viewType) {
		this.viewType = viewType;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	/**
	 * 查询版块列表
	 */
	public String list(){
		/*List<Forum> list =forumService.findAll();
		getValueStack().set("list", list);*/
		
		HQLHelper hh = new HQLHelper(Forum.class);
		hh.addOrderBy("o.position", true);
		
		PageBean pb = forumService.getPageBean(hh,currentPage);
		getValueStack().push(pb);
		return "list";
	}
	
	/**
	 * 查询主题列表（单个 版块）
	 */
	public String show(){
		//根据版块id查询板块，用于在页面显示
		Forum forum = forumService.getById(model.getId());
		getValueStack().push(forum);
		
		//根据版块id查询主题列表
		/*List<Topic> topicList = topicService.findTopicByForum(model);
		getValueStack().set("topicList", topicList);*/
		
		HQLHelper hh = new HQLHelper(Topic.class);
		hh.addWhere("o.forum = ?", model);
		if(viewType == 1){
			//查询精华帖
			hh.addWhere("o.type = ?", 1);
		}
		if(orderBy == 0){
			//<option value="0">默认排序（按最后更新时间排序，但所有置顶帖都在前面）</option>
			hh.addOrderBy("CASE o.type WHEN 2 THEN 2 ELSE 1 END", false);
			hh.addOrderBy("o.postTime", false);
		}else if(orderBy == 1){
			//<option value="1">按最后更新时间排序</option>
			hh.addOrderBy("o.lastUpdateTime", asc);
		}else if(orderBy == 2){
			//<option value="2">按主题发表时间排序</option>
			hh.addOrderBy("o.postTime", asc);
		}else{
			//<option value="3">按回复数量排序</option>
			hh.addOrderBy("o.replyCount", asc);
		}
		
		PageBean pb = topicService.getPageBean(hh,currentPage);
		getValueStack().push(pb);
		return "forumShow";
	}
}
