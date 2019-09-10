package cn.itcast.oa.domain;

import java.util.List;

/**
 * 封装分页信息
 * @author zhaoqx
 *
 */
public class PageBean {
	/**从页面提交过来的参数**/
	private int currentPage;//----当前页码
	private int pageSize;//-------每页显示多少条数据
	
	/**查询数据库获得**/
	private int recordCount;//----总记录数
	private List recordList;//页面要显示的数据集合
	
	/**由上面4个计算获得**/
	private int pageCount;//------总页数
	private int beginPageIndex;//-开始页码
	private int endPageIndex;//---结束页码
	
	public PageBean() {}

	public PageBean(int currentPage, int pageSize, int recordCount,List recordList) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		this.recordList = recordList;
		
		pageCount = (this.recordCount + this.pageSize - 1) / this.pageSize;//计算页数
		
		if(pageCount <= 10){
			this.beginPageIndex = 1;
			this.endPageIndex = this.pageCount;
		}else{
			this.beginPageIndex = this.currentPage - 4;
			this.endPageIndex = this.currentPage + 5;
			
			if(this.beginPageIndex < 1){
				this.beginPageIndex = 1;
				this.endPageIndex = 10;
			}
			if(this.endPageIndex > this.pageCount){
				this.endPageIndex = this.pageCount;
				this.beginPageIndex = this.endPageIndex - 9;
			}
		}
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getBeginPageIndex() {
		return beginPageIndex;
	}

	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}

	public List getRecordList() {
		return recordList;
	}

	public void setRecordList(List recordList) {
		this.recordList = recordList;
	}
}
