package com.thinkgem.jeesite.modules.issue.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 区域Entity
 * @author ThinkGem
 * @version 2013-05-15
 */
@Entity
@Table(name = "t_issue_priority")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IssuePriority extends IdEntity<IssuePriority> {
	private static final long serialVersionUID = 6577883357305580128L;
	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}