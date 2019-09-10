package cn.itcast.oa.service;

import java.io.InputStream;
import java.util.List;

import cn.itcast.oa.domain.Template;

public interface ITemplateService {

	public List<Template> findAll();

	public void save(Template model);

	public void delete(Long id);

	public Template getById(Long id);

	public void update(Template template);

	public InputStream getInputStreamById(Long id);

}
