/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thinkgem.jeesite.modules.sys.web;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.DbUtils;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.BaseConstant;
import com.thinkgem.jeesite.modules.sys.entity.Db;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

/**
 * 用户Controller
 * @author ThinkGem
 * @version 2013-5-31
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/db")
public class DbController extends BaseController {

	@Autowired
	private SystemService systemService;
	
	@RequestMapping(value = {""})
	public String list(Db db,HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Db> page = systemService.findDb(
				new Page<Db>(request, response), db);
		model.addAttribute("page",page);
		return "modules/sys/dbForm";
	}

	@RequestMapping(value = {"back"})
	public String back(HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes) {
		String realPath = request.getSession().getServletContext().getRealPath("/");
		String fileSavePath = "files"+File.separator+"dbsqls"+File.separator+DateUtils.getYear()+File.separator+DateUtils.getMonth()+File.separator;
		String fileName = BaseConstant.DBCONFIG.get(BaseConstant.JDBC_DB_NAME)+new Date().getTime()+".sql";
		String filePath = realPath+fileSavePath;
		FileUtils.createDirectory(filePath);
		System.out.println(filePath+fileName);
		BaseConstant.DBCONFIG.put(BaseConstant.JDBC_DB_FILEPATH, filePath+fileName);
		if(DbUtils.backup()){
			Db db = new Db();
			//保存的文件名
			db.setFileName(fileName);
			//保存的文件相对路径
			db.setFilePath(request.getSession().getServletContext().getContextPath()+File.separator+fileSavePath+fileName);
			systemService.saveMail(db);
			addMessage(redirectAttributes, "数据库备份成功");
		}else{
			addMessage(redirectAttributes, "数据库备份失败");
		}
		return "redirect:"+Global.getAdminPath()+"/sys/db";
	}
	
	
	
	

}
