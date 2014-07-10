/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thinkgem.jeesite.modules.issue.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.MailUtil;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.issue.Constant;
import com.thinkgem.jeesite.modules.issue.entity.IssueInfo;
import com.thinkgem.jeesite.modules.issue.entity.IssueInfoDealHistory;
import com.thinkgem.jeesite.modules.issue.entity.IssueLevel;
import com.thinkgem.jeesite.modules.issue.entity.IssuePriority;
import com.thinkgem.jeesite.modules.issue.entity.IssueProject;
import com.thinkgem.jeesite.modules.issue.entity.IssueProjectVersion;
import com.thinkgem.jeesite.modules.issue.entity.IssueRegularity;
import com.thinkgem.jeesite.modules.issue.entity.IssueType;
import com.thinkgem.jeesite.modules.issue.service.IssueProjectService;
import com.thinkgem.jeesite.modules.issue.service.IssueProjectVersionService;
import com.thinkgem.jeesite.modules.issue.service.IssuePropertiesService;
import com.thinkgem.jeesite.modules.issue.service.IssueService;
import com.thinkgem.jeesite.modules.sys.entity.Mail;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 区域Controller
 * @author ThinkGem
 * @version 2013-5-15
 */
@Controller
@RequestMapping(value = "${adminPath}/issue/info")
public class IssueInfoController extends BaseController {

	@Autowired
	private IssueService issueService;
	@Autowired
	private IssueProjectService projectService;
	@Autowired
	private IssuePropertiesService propertiesService;
	@Autowired
	private IssueProjectVersionService projectVersionService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private SystemService systemService;

	
	@ModelAttribute("issueInfo")
	public IssueInfo get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return issueService.get(id);
		}else{
			IssueInfo info = new IssueInfo();
			info.setContent(baseContext());
			return info;
		}
	}
	
	@ModelAttribute("history")
	public IssueInfoDealHistory getHistory(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return issueService.getHistory(id);
		}else{
			return new IssueInfoDealHistory();
		}
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(IssueInfo issueInfo,  HttpServletRequest request, HttpServletResponse response, Model model) {
		//我的缺陷
		if(Constant.ISSUE_STATUS_DEFAULT.equalsIgnoreCase(issueInfo.getStatus())){
			issueInfo.setCreateBy(UserUtils.getUser());
		}
		
		if(Constant.ISSUE_STATUS_PEDING.equalsIgnoreCase(issueInfo.getStatus())){
			issueInfo.setUser(UserUtils.getUser());
		}
		
		Page<IssueInfo> page = issueService.findIssue(new Page<IssueInfo>(request, response), issueInfo);
		List<IssueProject> projects = projectService.findAll();
		
		model.addAttribute("issueInfo",issueInfo);
		model.addAttribute("projects",projects);
        model.addAttribute("page", page);
		return "modules/issue/info/issueList";
	}
	
	@RequestMapping(value = {"completelist"})
	public String completelist(IssueInfoDealHistory history,  HttpServletRequest request, HttpServletResponse response, Model model) {
		
		Page<IssueInfoDealHistory> page = issueService.findIssueHistory(new Page<IssueInfoDealHistory>(request, response), history);
		List<IssueProject> projects = projectService.findAll();
		
		model.addAttribute("history",history);
		model.addAttribute("projects",projects);
        model.addAttribute("page", page);
		return "modules/issue/info_history/issueHistoryList";
	}
	
	
	@RequestMapping(value = {"view"})
	public String view(IssueInfo issueInfo,IssueInfoDealHistory history,  HttpServletRequest request, HttpServletResponse response, Model model) {
		history.setId(null);
		history.setStatus("0");
		List<User> users = new ArrayList<User>();
		Office office = issueInfo.getOffice();
		if(office!=null&&StringUtils.isNotEmpty(office.getId())){
			users = office.getUserList();
		}
		history.setInfo(issueInfo);
		List<Office> offices = officeService.findAll();
			
		model.addAttribute("users",users);
		model.addAttribute("offices",offices);
		model.addAttribute("issueInfo",issueInfo);
		model.addAttribute("history",history);
		
		return "modules/issue/info/issueView";
	}
	
	@RequestMapping(value = {"allot"})
	public String allot(IssueInfo issueInfo,IssueInfoDealHistory history,  HttpServletRequest request, HttpServletResponse response, Model model) {
		history.setId(null);
		history.setStatus("0");
		List<User> users = new ArrayList<User>();
		Office office = issueInfo.getOffice();
		if(office!=null&&StringUtils.isNotEmpty(office.getId())){
			users = office.getUserList();
		}
		history.setInfo(issueInfo);
		List<Office> offices = officeService.findAll();
			
		model.addAttribute("users",users);
		model.addAttribute("offices",offices);
		model.addAttribute("issueInfo",issueInfo);
		model.addAttribute("history",history);
		
		return "modules/issue/info/issueInfo";
	}
	
	@RequestMapping(value = {"form"})
	public String form(IssueInfo issueInfo, Model model) {
		
		List<IssueProjectVersion> projectVersions = new ArrayList<IssueProjectVersion>();
		List<User> users = new ArrayList<User>();
		IssueProject project = issueInfo.getProject();
		if(project!=null&&StringUtils.isNotEmpty(project.getId())){
			projectVersions = projectVersionService.findAll(project,new IssueProjectVersion());
		}
		Office office = issueInfo.getOffice();
		if(office!=null&&StringUtils.isNotEmpty(office.getId())){
			users = office.getUserList();
		}
		
		List<IssueProject> projects = projectService.findAll();
		List<IssueType> types = propertiesService.findTypes();
		List<IssueLevel> levels = propertiesService.findLevels();
		List<IssuePriority> prioritys = propertiesService.findPrioritys();
		List<IssueRegularity> regularitys = propertiesService.findRegularitys();
		List<Office> offices = officeService.findAll();
		
		model.addAttribute("offices",offices);
		model.addAttribute("issueInfo",issueInfo);
		model.addAttribute("users",users);
		model.addAttribute("types",types);
		model.addAttribute("levels",levels);
		model.addAttribute("projects",projects);
		model.addAttribute("prioritys",prioritys);
		model.addAttribute("regularitys",regularitys);
		model.addAttribute("projectVersions",projectVersions);
		
		return "modules/issue/info/issueForm";
	}

	@RequestMapping(value = {"save"})
	public String save(IssueInfo issueInfo, Model model, RedirectAttributes redirectAttributes) {
		issueService.save(issueInfo);
		if(StringUtils.isNotEmpty(issueInfo.getNotice())&&StringUtils.equals(issueInfo.getNotice(), Constant.YES)){
			Mail mailConfig = systemService.getMailDefault();
			MailUtil.sendAndCc(mailConfig.getSmtp(), mailConfig.getUsername(), "358684194@qq.com", null, "测试主题", "测试内容", mailConfig.getUsername(), mailConfig.getPassword(), null);
		}
		addMessage(redirectAttributes, getOperStatus(issueInfo.getId())+"缺陷'" + issueInfo.getIssueTitle() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/issue/info/list";
	}
	
	@RequestMapping(value = {"del"})
	public String del(IssueInfo issueInfo, Model model, RedirectAttributes redirectAttributes) {
		issueService.delete(issueInfo.getId());
		model.addAttribute("issue",issueInfo);
		addMessage(redirectAttributes, "删除缺陷'" + issueInfo.getIssueTitle() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/issue/info/list";
	}
	
	
	//变更历史记录
	@RequestMapping(value = {"history/save"})
	public String historySave(IssueInfoDealHistory history, Model model, RedirectAttributes redirectAttributes) {
		
		IssueInfo info = history.getInfo();
		
		if (!beanValidator(model, history)){
			return form(history.getInfo(), model);
		}
		
		User user = info.getUser();
		Office office = info.getOffice();
		if(StringUtils.isNotEmpty(user.getId())){
			user = systemService.getUser(user.getId());
			
		}
		if(StringUtils.isNotEmpty(office.getId())){
			office = officeService.get(office.getId());
			
		}
		
		if(StringUtils.isNotEmpty(info.getId())){
			info = issueService.get(info.getId());
			info.setUser(user);
			info.setStatus(history.getStatus());
			info.setOffice(office);
		}
		
		history.setInfo(info);
		issueService.save(info);
		issueService.save(history);

		addMessage(redirectAttributes, getOperStatus(history.getId())+"缺陷'" + history.getRemarks() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/issue/info/list";
	}
	
}
