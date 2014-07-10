/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thinkgem.jeesite.modules.issue.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.issue.entity.IssueProject;
import com.thinkgem.jeesite.modules.issue.entity.IssueProjectVersion;
import com.thinkgem.jeesite.modules.issue.service.IssueProjectService;
import com.thinkgem.jeesite.modules.issue.service.IssueProjectVersionService;
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
@RequestMapping(value = "${adminPath}/issue/project")
public class IssueProjectController extends BaseController {

	@Autowired
	private IssueProjectService projectService;
	@Autowired
	private IssueUserToProjectService userToProjectService;

	@Autowired
	private IssueProjectVersionService projectVersionService;
	@Autowired
	private OfficeService officeService;
	
	@Autowired
	private SystemService systemService;
	
	
	@ModelAttribute("issueProject")
	public IssueProject get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return projectService.get(id);
		}else{
			return new IssueProject();
		}
	}
	
	

	@RequestMapping(value = { "list", "" })
	public String list(IssueProject issueProject, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<IssueProject> page = projectService.findIssue(
				new Page<IssueProject>(request, response), issueProject);
		model.addAttribute("issueProject", issueProject);
		model.addAttribute("page", page);
		return "modules/issue/project/projectList";
	}

	@RequestMapping(value = { "form" })
	public String form(IssueProject issueProject, Model model) {
//		if (StringUtils.isNotEmpty(project.getId())) {
//			project = projectService.get(project.getId());
//		}
		model.addAttribute("issueProject", issueProject);
		return "modules/issue/project/projectForm";
	}
	
	

	@RequestMapping(value = { "save" })
	public String save(IssueProject issueProject, Model model,
			RedirectAttributes redirectAttributes) {
		projectService.save(issueProject);
		addMessage(redirectAttributes, "添加项目'" + issueProject.getProjectName()
				+ "'成功");
		return "redirect:" + Global.getAdminPath() + "/issue/project/list";
	}

	@RequestMapping(value = { "del" })
	public String del(IssueProject issueProject, Model model,
			RedirectAttributes redirectAttributes) {
		if (StringUtils.isNotEmpty(issueProject.getId())) {
			issueProject = projectService.get(issueProject.getId());
			projectService.delete(issueProject.getId());
		}
		model.addAttribute("issueProject", issueProject);
		addMessage(redirectAttributes, "删除项目'" + issueProject.getProjectName()
				+ "'成功");
		return "redirect:" + Global.getAdminPath() + "/issue/project/list";
	}

	@RequestMapping(value = { "version" })
	public String version(IssueProjectVersion version, IssueProject issueProject, Model model,
			RedirectAttributes redirectAttributes) {
		
		if (StringUtils.isNotEmpty(issueProject.getId())) {
			issueProject = projectService.get(issueProject.getId());
		}
		List<IssueProjectVersion> projectVersions = projectVersionService
				.findAll(issueProject,version);
		model.addAttribute("issueProject", issueProject);
		model.addAttribute("version",version);
		model.addAttribute("projectVersions", projectVersions);
		return "modules/issue/project/versionList";
	}

	@ResponseBody
	@RequestMapping(value = "version/save")
	public Map<String, Object> versionSave(String id,
			String versionNo,  String versionId,  String status,HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		Map<String,Object> map = new HashMap<String,Object>();
		IssueProjectVersion projectVersion = new IssueProjectVersion();
		if(StringUtils.isNotEmpty(versionId)){
			projectVersion = projectVersionService.get(versionId);
		}
		projectVersion.setVersionNo(versionNo);
		projectVersion.setStatus(status);
		IssueProject project = projectService.get(id);
		projectVersion.setProject(project);
		projectVersionService.save(projectVersion);
		List<IssueProjectVersion> projectVersions = projectVersionService
				.findAll(project,new IssueProjectVersion());
		map.put("result", "true");
		map.put("value", projectVersions);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "version/del")
	public Map<String, Object> versionDel(String id,
			String projectId, HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		Map<String,Object> map = new HashMap<String,Object>();
		IssueProject project = projectService.get(projectId);
		projectVersionService.delete(id);
		List<IssueProjectVersion> projectVersions = projectVersionService
				.findAll(project,new IssueProjectVersion());
		map.put("result", "true");
		map.put("value", projectVersions);
		return map;
	}
	
	@RequestMapping(value = "version/form")
	public String versionEdit(String id, Model model,
			RedirectAttributes redirectAttributes) {
		IssueProjectVersion version = new IssueProjectVersion();
		if(StringUtils.isNotEmpty(id)){
			version = projectVersionService.get(id);
		}
		model.addAttribute("version",version);
		return  "modules/issue/project/versionForm";
	}
	
	
	@RequestMapping(value = "version/save1")
	public String versionSave1(IssueProjectVersion version, Model model,
			RedirectAttributes redirectAttributes) {
		
		
		return  "modules/issue/project/versionList";
	}
	
	@ResponseBody
	@RequestMapping(value = "version/list")
	public Map<String, Object> versionList(String id,
			HttpServletResponse response) {
		Map<String,Object> map = new HashMap<String,Object>();
		response.setContentType("application/json; charset=UTF-8");
		List<IssueProjectVersion> projectVersions = new ArrayList<IssueProjectVersion>();
		IssueProjectVersion searcher = new IssueProjectVersion();
		searcher.setStatus("0");
		if(StringUtils.isNotEmpty(id)){
			IssueProject project = projectService.get(id);
			projectVersions = projectVersionService
					.findAll(project,searcher);
		}
		map.put("result", "true");
		map.put("value", projectVersions);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "user/list")
	public Map<String, Object> userList(String id,
			HttpServletResponse response) {
		Map<String,Object> map = new HashMap<String,Object>();
		response.setContentType("application/json; charset=UTF-8");
		List<User> users = new ArrayList<User>();
		if(StringUtils.isNotEmpty(id)){
			Office office = officeService.get(id);
			users = office.getUserList();
		}
		map.put("result", "true");
		map.put("value", users);
		return map;
	}
}
