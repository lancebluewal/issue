/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thinkgem.jeesite.modules.home.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.home.entity.Home;
import com.thinkgem.jeesite.modules.home.service.HomeService;

/**
 * 区域Controller
 * @author ThinkGem
 * @version 2013-5-15
 */
@Controller
@RequestMapping(value = "${adminPath}/home")
public class HomeController extends BaseController {

	private HomeService homeService;
	
	@ModelAttribute("issueInfo")
	public Home get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return homeService.get(id);
		}else{
			return new Home();
		}
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Home home,  HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "modules/home/index";
	}
	
	@RequestMapping(value = {"save"})
	public String save(Home home,  HttpServletRequest request, HttpServletResponse response, Model model) {
		
		
		return "redirect:"+Global.getAdminPath()+"/home";
	}
	
	
}
