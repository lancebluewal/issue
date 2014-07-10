/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thinkgem.jeesite.modules.issue.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.issue.entity.IssueProject;
import com.thinkgem.jeesite.modules.issue.service.IssueProjectService;
import com.thinkgem.jeesite.modules.issue.service.IssueUserToProjectService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

/**
 * 区域Controller
 * 
 * @author ThinkGem
 * @version 2013-5-15
 */
@Controller
@RequestMapping(value = "${adminPath}/issue/project/user")
public class IssueUserToProjectController extends BaseController {
	
	@Autowired
	private OfficeService officeService;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private IssueUserToProjectService userToProjectService;
	
	@Autowired
	private IssueProjectService projectService;
	
	
	@RequestMapping(value = { "" })
	public String user(IssueProject issueProject,String officeId, Model model) {
		List<Office> offices = officeService.findAll();
		List<User> users = offices.get(0).getUserList();
		if(StringUtils.isNotEmpty(officeId)){
			Office office = officeService.get(officeId);
			issueProject.setSearch(office);
			users = office.getUserList();
		}
		
		if(StringUtils.isNotEmpty(issueProject.getId())){
			IssueProject projec = projectService.get(issueProject.getId());
			issueProject.setUserList(projec.getUserList());
		}
		model.addAttribute("users",users);
		model.addAttribute("issueProject", issueProject);
		model.addAttribute("offices",offices);
		return "modules/issue/project/projectUserForm";
	}
	
	@RequestMapping(value={"save"})
	public String userSave(IssueProject issueProject, Model model,RedirectAttributes redirectAttributes) {
		userToProjectService.saveUserToProject(issueProject,issueProject.getUserIdList());
		List<Office> offices = officeService.findAll();
		model.addAttribute("issueProject", issueProject);
		model.addAttribute("offices",offices);
		return "redirect:" + Global.getAdminPath() + "/issue/project/list";
	}
}
