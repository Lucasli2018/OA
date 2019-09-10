package cn.itcast.oa.domain;

import org.jbpm.api.task.Task;

/**
 * 包装申请信息和任务信息
 * @author zhaoqx
 *
 */
public class TaskView {
	private Application application;
	private Task task;
	
	public TaskView(Application application, Task task) {
		this.application = application;
		this.task = task;
	}

	public TaskView() {}
	
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
}
