/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thinkgem.jeesite.modules.sys.dao;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.sys.entity.Mail;

/**
 * 区域DAO接口
 * @author ThinkGem
 * @version 2013-8-23
 */
@Repository
public class MailDao extends BaseDao<Mail> {
	
	public int setUnUse(String id){
		return update("update Mail set asdefault='0' where id!=:p1",new Parameter(id));
	}
}
