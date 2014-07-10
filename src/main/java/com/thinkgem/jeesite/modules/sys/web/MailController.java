/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thinkgem.jeesite.modules.sys.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.issue.Constant;
import com.thinkgem.jeesite.modules.sys.entity.Mail;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

/**
 * 用户Controller
 * @author ThinkGem
 * @version 2013-5-31
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/mail")
public class MailController extends BaseController {

	@Autowired
	private SystemService systemService;
	
	@RequestMapping(value = {"list", ""})
	public String list(Mail mail, HttpServletRequest request, HttpServletResponse response, Model model) {
        List<Mail> page = systemService.findMailConfigAll(); 
        model.addAttribute("page", page);
		return "modules/sys/mailList";
	}

	@RequestMapping(value = "form")
	public String form(Mail mail, Model model) {
		if(StringUtils.isNotEmpty(mail.getId())){
			mail = systemService.getMail(mail.getId());
		}
		model.addAttribute("mail", mail);
		return "modules/sys/mailForm";
	}

	@RequestMapping(value = "save")
	public String save(Mail mail, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mail)){
			return form(mail, model);
		}
		systemService.saveMail(mail);
		if(StringUtils.isNotEmpty(mail.getId())&&StringUtils.isNotEmpty(mail.getAsdefault())&&StringUtils.equals(mail.getAsdefault(), Constant.DEFAULT)){
			systemService.setToUnUse(mail.getId());
		}
		addMessage(redirectAttributes, "保存邮件设置成功");
		return "redirect:"+Global.getAdminPath()+"/sys/mail";
	}
	
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		
		systemService.deleteMail(id);
		addMessage(redirectAttributes, "删除邮件配置信息成功");
		return "redirect:"+Global.getAdminPath()+"/sys/mail";
	}
	

}
