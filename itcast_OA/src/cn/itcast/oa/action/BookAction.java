package cn.itcast.oa.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Book;

@Controller
@Scope("prototype")
public class BookAction extends BaseAction<Book>{
	public String execute() throws Exception {
		System.out.println(model);
		
		//bookService.save(model);
		bookService.delete(model.getId());
		return NONE;
	}
}
