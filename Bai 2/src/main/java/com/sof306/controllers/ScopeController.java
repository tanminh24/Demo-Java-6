package com.sof306.controllers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ScopeController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping("/scope")
	public String index(Model model) {
		model.addAttribute("a","Model");
		request.setAttribute("b", "Request");
		session.setAttribute("c", "Session");
		servletContext.setAttribute("d", "Application Scope");
		return "scope/index";
	}
	
}
