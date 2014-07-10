/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thinkgem.jeesite.modules.issue.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 区域Entity
 * @author ThinkGem
 * @version 2013-05-15
 */
@Entity
@Table(name = "t_issue_info_deal_history")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IssueInfoDealHistory extends IdEntity<IssueInfoDealHistory> {
	private static final long serialVersionUID = -6662133902649492948L;
	
	private IssueInfo info;
	
	private String status;
	
	
	
	public IssueInfoDealHistory(){
		super();
	}
	
	public IssueInfoDealHistory(String id){
		this();
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="issue_info_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	public IssueInfo getInfo() {
		return info;
	}

	public void setInfo(IssueInfo info) {
		this.info = info;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}