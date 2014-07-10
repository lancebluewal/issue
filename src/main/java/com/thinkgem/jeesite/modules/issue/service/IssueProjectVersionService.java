/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thinkgem.jeesite.modules.issue.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.issue.dao.IssueProjectVersionDao;
import com.thinkgem.jeesite.modules.issue.entity.IssueProject;
import com.thinkgem.jeesite.modules.issue.entity.IssueProjectVersion;

/**
 * 区域Service
 * @author ThinkGem
 * @version 2013-5-29
 */
@Service
@Transactional(readOnly = true)
public class IssueProjectVersionService extends BaseService {

	@Autowired
	private IssueProjectVersionDao projectVersionDao;
	
	public IssueProjectVersion get(String id) {
		return projectVersionDao.get(id);
	}
	
	public Page<IssueProjectVersion> findIssue(Page<IssueProjectVersion> page, IssueProjectVersion issueProjectVersion) {
		DetachedCriteria dc = projectVersionDao.createDetachedCriteria();
		if(StringUtils.isNotEmpty(issueProjectVersion.getVersionNo())){
			dc.add(Restrictions.ilike("versionNo", "%"+issueProjectVersion.getVersionNo()+"%"));
		}
		dc.add(Restrictions.eq(IssueProjectVersion.FIELD_DEL_FLAG, IssueProjectVersion.DEL_FLAG_NORMAL));
		return projectVersionDao.find(page, dc);
	}

	@Transactional(readOnly = false)
	public void save(IssueProjectVersion rojectVersion) {
		projectVersionDao.save(rojectVersion);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) { 
		projectVersionDao.deleteById(id);
	}
	
	
	public List<IssueProjectVersion> findAll(IssueProject project,IssueProjectVersion version){
		DetachedCriteria dc = projectVersionDao.createDetachedCriteria();
		if(StringUtils.isNotEmpty(project.getId())){
			dc.createAlias("project", "project");
			dc.add(Restrictions.eq("project.id", project.getId()));
		}
		
		if(StringUtils.isNotEmpty(version.getStatus())){
			dc.add(Restrictions.eq("status", version.getStatus()));
		}
		
		if(StringUtils.isNotEmpty(version.getVersionNo())){
			dc.add(Restrictions.ilike("versionNo","%"+ version.getVersionNo()+"%"));
		}
		
		dc.add(Restrictions.eq(IssueProjectVersion.FIELD_DEL_FLAG, IssueProjectVersion.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("createDate"));
		return projectVersionDao.find(dc);
	}
}
