/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thinkgem.jeesite.modules.issue.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.issue.dao.IssueInfoDao;
import com.thinkgem.jeesite.modules.issue.dao.IssueInfoDealHistoryDao;
import com.thinkgem.jeesite.modules.issue.entity.IssueInfo;
import com.thinkgem.jeesite.modules.issue.entity.IssueInfoDealHistory;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 区域Service
 * @author ThinkGem
 * @version 2013-5-29
 */
@Service
@Transactional(readOnly = true)
public class IssueService extends BaseService {

	@Autowired
	private IssueInfoDao issueDao;
	
	@Autowired
	private IssueInfoDealHistoryDao historyDao;
	
	public IssueInfo get(String id) {
		return issueDao.get(id);
	}
	
	public Page<IssueInfoDealHistory> findIssueHistory(Page<IssueInfoDealHistory> page, IssueInfoDealHistory history){
		DetachedCriteria dc = historyDao.createDetachedCriteria();
		if((history.getCreateBy()!=null&&StringUtils.isNotEmpty(history.getCreateBy().getId()))){
			dc.add(Restrictions.eq("createBy.id", history.getCreateBy().getId()));
		}
		
		if(StringUtils.isNotEmpty(history.getStatus())){
			dc.add(Restrictions.in("status",history.getStatus().split(",")));
		}
		
		if(history.getInfo()!=null&&history.getInfo().getProject()!=null&&StringUtils.isNotEmpty(history.getInfo().getProject().getId())){
			dc.createAlias("info", "info");
			dc.add(Restrictions.eq("info.project.id",history.getInfo().getProject().getId() ));
		}
		
		dc.add(Restrictions.eq(IssueInfo.FIELD_DEL_FLAG, IssueInfo.DEL_FLAG_NORMAL));
		return historyDao.find(page, dc);
	}
	
	public Page<IssueInfo> findIssue(Page<IssueInfo> page, IssueInfo issue) {
		DetachedCriteria dc = issueDao.createDetachedCriteria();
		if(StringUtils.isNotEmpty(issue.getIssueTitle())){
			dc.add(Restrictions.ilike("issueTitle", "%"+issue.getIssueTitle()+"%"));
		}
		
		if(issue.getProject()!=null&&StringUtils.isNotEmpty(issue.getProject().getId())){
			dc.add(Restrictions.eq("project.id",issue.getProject().getId()));
		}
		
		if((issue.getCreateBy()!=null&&StringUtils.isNotEmpty(issue.getCreateBy().getId()))&&!(issue.getUser()!=null&&StringUtils.isNotEmpty(issue.getUser().getId()))){
			dc.add(Restrictions.eq("createBy.id", issue.getCreateBy().getId()));
		}
		
		if(issue.getUser()!=null&&StringUtils.isNotEmpty(issue.getUser().getId())&&!(issue.getCreateBy()!=null&&StringUtils.isNotEmpty(issue.getCreateBy().getId()))){
			dc.add(Restrictions.eq("user.id", issue.getUser().getId()));
		}
		
		if(issue.getUser()!=null&&StringUtils.isNotEmpty(issue.getUser().getId())&&(issue.getCreateBy()!=null&&StringUtils.isNotEmpty(issue.getCreateBy().getId()))){
			dc.add(Restrictions.or(Restrictions.eq("createBy.id", issue.getCreateBy().getId()),Restrictions.eq("user.id", issue.getUser().getId())));
		}
		
		if(StringUtils.isNotEmpty(issue.getStatus())){
			dc.add(Restrictions.in("status",issue.getStatus().split(",")));
		}
		
		dc.add(Restrictions.eq(IssueInfo.FIELD_DEL_FLAG, IssueInfo.DEL_FLAG_NORMAL));
		return issueDao.find(page, dc);
	}

	@Transactional(readOnly = false)
	public void save(IssueInfo issue) {
		if (issue.getContent()!=null){
			issue.setContent(StringEscapeUtils.unescapeHtml4(
					issue.getContent()));
		}
		issueDao.save(issue);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) { 
		issueDao.deleteById(id);
	}
	
	
	
	
	//历史记录
	@Transactional(readOnly = false)
	public void save(IssueInfoDealHistory history){
		historyDao.save(history);
	}
	
	public IssueInfoDealHistory getHistory(String id){
		return historyDao.get(id);
	}
	
}
