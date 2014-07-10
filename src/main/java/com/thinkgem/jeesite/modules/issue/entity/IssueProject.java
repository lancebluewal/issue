/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thinkgem.jeesite.modules.issue.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.IdEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 区域Entity
 * @author ThinkGem
 * @version 2013-05-15
 */
@Entity
@Table(name = "t_issue_project")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IssueProject extends IdEntity<IssueProject> {
	private static final long serialVersionUID = -2152549632136549549L;
	private String projectName;
	private String status;
	
	private Office search;  
	
	
	@Transient
	public Office getSearch() {
		return search;
	}
	@Transient
	public void setSearch(Office search) {
		this.search = search;
	}

	private List<User> userList = Lists.newArrayList(); // 拥有角色列表
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "t_user_to_project", joinColumns = { @JoinColumn(name = "project_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
	@Where(clause="del_flag='"+DEL_FLAG_NORMAL+"'")
	@OrderBy("id")
	@NotFound(action = NotFoundAction.IGNORE)
	public List<User> getUserList() {
		return userList;
	}
	
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	@Transient
	@JsonIgnore
	public List<String> getUserIdList() {
		List<String> userIdList = Lists.newArrayList();
		for (User user : userList) {
			userIdList.add(user.getId());
		}
		return userIdList;
	}

	@Transient
	public void setUserIdList(List<String> userIdList) {
		userList = Lists.newArrayList();
		for (String userId : userIdList) {
			User user = new User();
			user.setId(userId);
			userList.add(user);
		}
	}
	
	public IssueProject(){
		super();
	}
	
	public IssueProject(String id){
		this();
		this.id = id;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	

	
}