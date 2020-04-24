package com.springlearn.springbean.bean_scope.web.controller;

import com.springlearn.iocoverview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 首页 Spring Web MVC Controller
 *
 * @author <a herf="mailto:dandanwdn@163.com">Wang Danning</a>
 * @since 2020-04-18
 **/
@Controller
public class IndexController {

	@Autowired
	private User user;


	@RequestMapping("/index")
	@ResponseBody
	public String index() {
		return user.getName() + user.getId();
	}

	@RequestMapping("/index1.html")
	public String index1(Model model) {
		// JSP EL 变量搜索路径 page -> request -> session -> application(ServletContext)
		model.addAttribute("userObject", user);
		return user.getName() + user.getId();
	}
}
