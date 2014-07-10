package com.thinkgem.jeesite.modules.issue.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.issue.dao.IssueProjectDao;
import com.thinkgem.jeesite.modules.issue.dao.UserToProjectDao;
import com.thinkgem.jeesite.modules.issue.entity.IssueProject;
import com.thinkgem.jeesite.modules.issue.entity.UserToProject;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.User;

@Service
@Transactional(readOnly = true)
public class IssueProjectService extends BaseService {


	@Autowired
	private IssueProjectDao projectDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserToProjectDao userToProjectDao;
	
	public IssueProject get(String id) {
		return projectDao.get(id);
	}
	
	public Page<IssueProject> findIssue(Page<IssueProject> page, IssueProject project) {
		DetachedCriteria dc = projectDao.createDetachedCriteria();
		if(StringUtils.isNotEmpty(project.getProjectName())){
			dc.add(Restrictions.ilike("projectName", "%"+project.getProjectName()+"%"));
		}
		dc.add(Restrictions.eq(IssueProject.FIELD_DEL_FLAG, IssueProject.DEL_FLAG_NORMAL));
		return projectDao.find(page, dc);
	}

	@Transactional(readOnly = false)
	public void save(IssueProject roject) {
		projectDao.save(roject);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) { 
		projectDao.deleteById(id);
	}
	
	
	public List<IssueProject> findAll(){
		DetachedCriteria dc = projectDao.createDetachedCriteria();
		dc.add(Restrictions.eq(IssueProject.FIELD_DEL_FLAG, IssueProject.DEL_FLAG_NORMAL));
		return projectDao.find(dc);
	}
	
}
