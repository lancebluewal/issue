/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;

/**
 * 项目Controller
 * @author liuj
 * @version 2013-12-07
 */
@Controller
@RequestMapping(value = "${adminPath}/daohang")
public class IndexController extends BaseController {

	@RequestMapping(value = "")
	public String form(Model model) {
		return "modules/daohang/index";
	}
}
