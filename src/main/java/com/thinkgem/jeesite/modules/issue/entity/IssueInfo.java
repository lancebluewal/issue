/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thinkgem.jeesite.modules.issue.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.IdEntity;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.cms.utils.CmsUtils;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 区域Entity
 * @author ThinkGem
 * @version 2013-05-15
 */
@Entity
@Table(name = "t_issue_info")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IssueInfo extends IdEntity<IssueInfo> {
	private static final long serialVersionUID = 2470457792297141275L;

	private String issueTitle;//缺陷标题
	
	private String image;	// 文章图片
	
	private String attachment;  //附件
	
	private String content;   //缺陷描述
	
	private IssueProject project; //项目
	
	private String notice;//是否通知
	
	private String status;
	
	private IssueProjectVersion projectVersion;  //项目版本
	
	private IssueType issueType;   //缺陷类型
	private IssueLevel issueLevel;  //缺陷级别
	private IssuePriority issuePriority;  //缺陷优先级
	private IssueRegularity issueRegularity;   //缺陷规律
	
	private IssueInfoDealHistory searchHistory;
	
	private List<IssueInfoDealHistory> history = Lists.newArrayList(); // 操作历史记录集合
	
	private Office office;
	
	private User user;
	
	
	public IssueInfo(){
		super();
	}
	
	public IssueInfo(String id){
		this();
		this.id = id;
	}
	
	
	
	
	@Transient
	public IssueInfoDealHistory getSearchHistory() {
		return searchHistory;
	}
	
	public void setSearchHistory(IssueInfoDealHistory searchHistory) {
		this.searchHistory = searchHistory;
	}

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="issue_info_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	public List<IssueInfoDealHistory> getHistory() {
		return history;
	}

	public void setHistory(List<IssueInfoDealHistory> history) {
		this.history = history;
	}

	@ManyToOne
	@JoinColumn(name="office_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ManyToOne
	@JoinColumn(name="type_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	public IssueType getIssueType() {
		return issueType;
	}

	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}

	@ManyToOne
	@JoinColumn(name="level_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	public IssueLevel getIssueLevel() {
		return issueLevel;
	}

	public void setIssueLevel(IssueLevel issueLevel) {
		this.issueLevel = issueLevel;
	}

	@ManyToOne
	@JoinColumn(name="priority_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	public IssuePriority getIssuePriority() {
		return issuePriority;
	}

	public void setIssuePriority(IssuePriority issuePriority) {
		this.issuePriority = issuePriority;
	}

	@ManyToOne
	@JoinColumn(name="regularity_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	public IssueRegularity getIssueRegularity() {
		return issueRegularity;
	}

	public void setIssueRegularity(IssueRegularity issueRegularity) {
		this.issueRegularity = issueRegularity;
	}

	@NotBlank
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@ManyToOne
	@JoinColumn(name="project_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	public IssueProject getProject() {
		return project;
	}

	public void setProject(IssueProject project) {
		this.project = project;
	}
	
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
        this.image = CmsUtils.formatImageSrcToDb(image);
	}
	
	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	@ManyToOne
	@JoinColumn(name="project_version_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	public IssueProjectVersion getProjectVersion() {
		return projectVersion;
	}

	public void setProjectVersion(IssueProjectVersion projectVersion) {
		this.projectVersion = projectVersion;
	}

	public String getIssueTitle() {
		return issueTitle;
	}

	public void setIssueTitle(String issueTitle) {
		this.issueTitle = issueTitle;
	}

	@Transient
   	public String getImageSrc() {
        return CmsUtils.formatImageSrcToWeb(this.image);
   	}

	public String getNotice() {
		return StringUtils.isEmpty(notice)?"0":notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}
	
	
	
}