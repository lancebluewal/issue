/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thinkgem.jeesite.modules.issue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.issue.dao.IssueLevelDao;
import com.thinkgem.jeesite.modules.issue.dao.IssuePriorityDao;
import com.thinkgem.jeesite.modules.issue.dao.IssueRegularityDao;
import com.thinkgem.jeesite.modules.issue.dao.IssueTypeDao;
import com.thinkgem.jeesite.modules.issue.entity.IssueLevel;
import com.thinkgem.jeesite.modules.issue.entity.IssuePriority;
import com.thinkgem.jeesite.modules.issue.entity.IssueRegularity;
import com.thinkgem.jeesite.modules.issue.entity.IssueType;

/**
 * 区域Service
 * @author ThinkGem
 * @version 2013-5-29
 */
@Service
@Transactional(readOnly = true)
public class IssuePropertiesService extends BaseService {

	
	@Autowired
	private IssueTypeDao typeDao;
	@Autowired
	private IssueLevelDao levelDao;
	@Autowired
	private IssuePriorityDao priorityDao;
	@Autowired
	private IssueRegularityDao regularityDao;
	
	
	public List<IssueType> findTypes(){
		return typeDao.findAll();
	}
	
	public List<IssueLevel> findLevels(){
		return levelDao.findAll();
	}
	
	public List<IssuePriority> findPrioritys(){
		return priorityDao.findAll();
	}
	
	public List<IssueRegularity> findRegularitys(){
		return regularityDao.findAll();
	}
 	
}
